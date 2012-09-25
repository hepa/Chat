package hu.unideb.inf.classes;


import hu.unideb.inf.forms.ViewServer;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Provides basic Server functions.
 *
 * @author Rendszergazda
 */
public class Server {

    ServerSocket host;
    ViewServer viewServer;
    Room defaultRoom;
    ArrayList<Room> Rooms = new ArrayList<>();
    int userID = 0;
    int port;
    int roomID = 0;

    public Server(ViewServer viewServer) {
        this.viewServer = viewServer;
    }

    public void listenSocket(int port) {
        this.port = port;
        try {
            host = new ServerSocket(port);
            viewServer.infoArea.append("Server is running...\n");
            System.out.println("Server is running...");
            this.createRoom("Default Room");
        } catch (IOException e) {
            viewServer.infoArea.append("Port is already open!\n");
            System.out.println("Port is already open!");
        }

        new Thread() {

            Socket sock;
            BufferedReader in = null;

            @Override
            public void run() {
                while (true) {
                    try {
                        sock = host.accept();
                        in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
                        String name = in.readLine();
                        Client client = new Client(sock, userID, name);
                        
                        viewServer.infoArea.append(name + " is connected: " 
                                + sock.getInetAddress() + "/ " + sock.getPort() + "\n");
                        System.out.println(name + " is connected: "
                                + sock.getInetAddress() + "/ " + sock.getPort());
                        
                        Rooms.get(0).addClient(client);
                        userID++;  
                    } catch (IOException ex) {
                        System.out.println(ex.getMessage());
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
        System.out.println("Room created: /name: " + room.getRoomName()
                + " /ID: " + room.getRoomID());
        
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
        System.out.println("Room deleted: \t/name: " + deletedRoomName
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
        for (Room r: Rooms) {
            rooms = rooms + ":" + r.getRoomName();
        }
        client.getOut().println(rooms);
        viewServer.infoArea.append("Rooms info sent.\n");
        System.out.println("Rooms info sent.");
    }
    
    public void sendRoomsName() {
        String rooms = "Rooms*:";
        for (Room r: Rooms) {
            rooms = rooms + r.getRoomName() + ":";
        }
        for (Room r: Rooms) {    
            for (Client c: r.Member) {
                c.getOut().println(rooms);
            }
        } 
    }
    
    public void moveClient(Room room, Client client) {
        String oldRoomName = client.getCurrentRoomName();
        room.addClient(client);
        viewServer.infoArea.append(client.getClientName() + " was moved from: "
                + oldRoomName + " to: " + room.getRoomName() + "\n");
        System.out.println(client.getClientName() + " was moved from: "
                + oldRoomName + " to: " + room.getRoomName());
    }
    
    public ArrayList<Room> getRooms() {
        return Rooms;
    }
}


