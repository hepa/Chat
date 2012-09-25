package hu.unideb.inf.classes;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class Client  {
    private Socket sock;
    private BufferedReader in = null;
    private PrintWriter out = null;
    
    private int clientID;
    private String clientName;
    private String currentRoomName;
    private int currentRoomID;
    private boolean online = false;
    
    public Client(Socket socket, int clientID, String clientName) {
        this.sock = socket;
        this.clientID = clientID;
        this.clientName = clientName;
        online = true;
        try {
            in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
            out = new PrintWriter(sock.getOutputStream(), true);
        } catch(IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void closeSocket() {
        try {
            in.close();
            out.close();
            sock.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public Socket getSock() {
        return sock;
    }
    
    public BufferedReader getIn() {
        return in;
    }
    
    public PrintWriter getOut() {
        return out;
    }

    public String getClientName() {
        return clientName;
    }
    
    public int getClientID() {
        return clientID;
    }   

    public String getCurrentRoomName() {
        return currentRoomName;
    }
    
    public int getCurrentRoomID() {
        return currentRoomID;
    }

    public boolean isOnline() {
        return online;
    }
    
    public void setCurrentRoomName(String roomName) {
        this.currentRoomName = roomName;
    }
    
    public void setCurrentRoomID(int roomID) {
        this.currentRoomID = roomID;
    }
}
