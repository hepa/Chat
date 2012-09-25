package hu.unideb.inf.classes;


import hu.unideb.inf.forms.RoomLogger;
import hu.unideb.inf.forms.ViewServer;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class Room {

    private int roomID;
    private String roomName;
    private Boolean isDeleted = false;
    ArrayList<Client> Member = new ArrayList<>();
    RoomLogger roomLogger;
    Server server;
    ViewServer viewServer;

    public Room(int roomID, String roomName, Server server, ViewServer viewServer) {
        this.roomID = roomID;
        this.roomName = roomName;
        this.server = server;
        this.viewServer = viewServer;
        roomLogger = new RoomLogger(null, true);
        roomLogger.setVisible(true);
        roomLogger.setTitle(roomName);
    }

    public void addClient(Client client) {
        client.setCurrentRoomName(roomName);
        client.setCurrentRoomID(roomID);
        Member.add(client);
        ClientThread clientThread = new ClientThread(client);
        Thread t = new Thread(clientThread);
        t.start();
        this.showClient();
        server.sendRoomsName(client);
        this.sendClientsName();
    }

    public void showClient() {
        int size = Member.size();
        final String clients[] = new String[size];
        for (int i = 0; i < size; i++) {
            clients[i] = Member.get(i).getClientName();
        }
        roomLogger.clientList.setModel(new javax.swing.AbstractListModel() {

            @Override
            public int getSize() {
                return clients.length;
            }

            @Override
            public Object getElementAt(int i) {
                return clients[i];
            }
        });
    }

    public void sendClientsName() {
        String clientsName = "Clients*";
        for (Client c : Member) {
            clientsName = clientsName + ":" + c.getClientName();
        }
        for (Client c : Member) {
            c.getOut().println(clientsName);
        }
    }

    public void deleteRoom() {
        for (Room r : server.Rooms) {
            if (r.roomID == 0) {
                for (Client c : Member) {
                    server.moveClient(r, c);
                }
            }
        }
        roomLogger.dispose();
        isDeleted = true;
    }

    public class ClientThread implements Runnable {

        Client client;

        public ClientThread(Client client) {
            this.client = client;
        }

        public boolean changeRoom(String buffer, Client client) {
            String check[] = buffer.split("=");
            if (check[0].equals("Room*")) {
                for (Room r : server.Rooms) {
                    if (r.roomName.equals(check[1])) {
                        Member.remove(client);
                        server.moveClient(r, client);
                        showClient();
                        sendClientsName();
                        return true;
                    }
                }
            }
            return false;
        }

        @Override
        public void run() {
            String buffer;

            while (true) {

                try {
                    if (isDeleted) {
                        break;
                    }
                    buffer = client.getIn().readLine();
                    if (isDeleted) {
                        break;
                    }
                    if (buffer == null) {
                        viewServer.infoArea.append(client.getClientName() + " on: "
                                + client.getSock().getInetAddress()
                                + ": " + client.getSock().getPort()
                                + " disconnected!\n");
                        System.out.println(client.getClientName() + " on: "
                                + client.getSock().getInetAddress()
                                + ": " + client.getSock().getPort()
                                + " disconnected!");
                        client.closeSocket();
                        Member.remove(client);
                        showClient();
                        sendClientsName();
                        break;
                    }
                    if (changeRoom(buffer, client)) {
                        break;
                    }

                    DateFormat dateFormat = new SimpleDateFormat(
                            "yyyy/MM/dd HH:mm:ss");
                    Calendar cal = Calendar.getInstance();
                    roomLogger.jTextArea1.append(client.getClientName().toUpperCase()
                            + " at [" + dateFormat.format(cal.getTime()) + "]:\n"
                            + buffer + "\n");
                    for (Client c : Member) {
                        c.getOut().println(client.getClientName() + ":" + buffer);
                    }
                } catch (IOException e) {
                    viewServer.infoArea.append(client.getClientName() + "on: "
                            + client.getSock().getInetAddress()
                            + ": " + client.getSock().getPort()
                            + " disconnected!\n");
                    System.out.println(client.getClientName() + " on: "
                            + client.getSock().getInetAddress()
                            + ": " + client.getSock().getPort()
                            + " disconnected!");
                    client.closeSocket();
                    Member.remove(client);
                    showClient();
                    sendClientsName();
                    break;
                }
            }

        }
    }

    public int getRoomID() {
        return roomID;
    }

    public String getRoomName() {
        return roomName;
    }
    
    public void roomLoggerVisible(boolean bool) {
        roomLogger.setVisible(bool);
    }
}
