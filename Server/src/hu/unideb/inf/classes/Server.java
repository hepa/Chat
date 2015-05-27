package hu.unideb.inf.classes;

import hu.unideb.inf.forms.ServerView;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Provides basic Server functions.
 *
 * @author Rendszergazda
 */
public class Server {

    private ServerSocket server;
    private ServerView viewServer;
    private ArrayList<Room> Rooms = new ArrayList<>();
    int userID = 0;
    int port;
    int roomID = 0;

    public Server(ServerView viewServer) {
        this.viewServer = viewServer;
    }

    public void listenSocket(int port) {
        this.port = port;
        try {
            server = new ServerSocket(port);
            viewServer.infoArea.append("Server is running at port: " + port + "\n");
            Logger.getLogger(Server.class.getName()).log(Level.INFO, "Server is running at port: " + port);
            this.createRoom("Default Room");
        } catch (IOException ex) {
            viewServer.infoArea.append("Port is already open!\n");
            Logger.getLogger(Server.class.getName()).log(Level.WARNING, "Port = " + port + " is already open!", ex);
        }

        new Thread() {

            Socket sock;
            BufferedReader in = null;

            @Override
            public void run() {
                while (true) {
                    try {
                        sock = server.accept();
                        in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
                        String name = in.readLine();
                        Client client = new Client(sock, userID, name);

                        viewServer.infoArea.append(name + " is connected: "
                                + sock.getInetAddress() + "/ " + sock.getPort() + "\n");
                        Logger.getLogger(Server.class.getName()).log(Level.INFO,
                                name + " is connected: " + sock.getInetAddress() + "/ " + sock.getPort());

                        Rooms.get(0).addClient(client);
                        userID++;
                    } catch (IOException ex) {
                        Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }.start();
    }

    public void createRoom(String roomName) {
        Room room = new Room(roomID, roomName, this, viewServer);
        Rooms.add(room);
        roomID++;

        viewServer.infoArea.append("Room created: \t/name: " + room.getRoomName()
                + " \t/ID: " + room.getRoomID() + "\n");
        Logger.getLogger(Server.class.getName()).log(Level.INFO,
                "Room created: /name: " + room.getRoomName() + " /ID: " + room.getRoomID());

        showRoomsName();
        sendRoomsName();
    }

    public void deleteRoom(Room room) {
        String deletedRoomName = room.getRoomName();
        int deletedRoomID = room.getRoomID();

        room.deleteRoom();
        Rooms.remove(room);

        viewServer.infoArea.append("Room deleted: \t/name: " + deletedRoomName
                + " \t/ID: " + deletedRoomID + "\n");
        Logger.getLogger(Server.class.getName()).log(Level.INFO,
                "Room deleted: \t/name: " + deletedRoomName
                + " \t/ID: " + deletedRoomID + "\n");

        showRoomsName();
        sendRoomsName();

    }

    public void showRoomsName() {
        int size = Rooms.size();
        final String rooms[] = new String[size];
        for (int i = 0; i < size; i++) {
            rooms[i] = Rooms.get(i).getRoomName();
        }
        viewServer.roomList.setModel(new javax.swing.AbstractListModel() {

            @Override
            public int getSize() {
                return rooms.length;
            }

            @Override
            public Object getElementAt(int i) {
                return rooms[i];
            }
        });
    }

    public void sendRoomsName(Client client) {
        String rooms = "Rooms*";
        for (Room r : Rooms) {
            rooms = rooms + ":" + r.getRoomName();
        }
        client.getOut().println(rooms);
        viewServer.infoArea.append("Rooms info sent.\n");
        Logger.getLogger(Server.class.getName()).log(Level.INFO,
                "Rooms info sent.\n");
    }

    public void sendRoomsName() {
        String rooms = "Rooms*:";
        for (Room room : Rooms) {
            rooms = rooms + room.getRoomName() + ":";
        }
        for (Room room : Rooms) {
            for (Client client : room.getMember()) {
                client.getOut().println(rooms);
            }
        }
    }

    public void moveClient(Room room, Client client) {
        String oldRoomName = client.getCurrentRoomName();
        room.addClient(client);
        viewServer.infoArea.append(client.getClientName() + " was moved from: "
                + oldRoomName + " to: " + room.getRoomName() + "\n");
        Logger.getLogger(Server.class.getName()).log(Level.INFO,
                client.getClientName() + " was moved from: "
                + oldRoomName + " to: " + room.getRoomName());
    }

    public ArrayList<Room> getRooms() {
        return Rooms;
    }
}
