package hu.unideb.inf.classes;

import hu.unideb.inf.forms.MessageView;
import hu.unideb.inf.forms.ClientView;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Connection {

    private Socket socket;
    String name;
    PrintWriter out;
    BufferedReader in;
    ClientView viewClient;
    MessageView messageView;

    public Connection(String ip, int port, String name, ClientView viewClient,
            MessageView messageView) {
        this.viewClient = viewClient;
        this.messageView = messageView;
        this.name = name;
        try {
            socket = new Socket(ip, port);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            viewClient.setIsConnected(true);
        } catch (IOException ex) {
            messageView.setMessageView("Unknown Host: " + ip + ":" + port,
                    "../images/Warning-icon.png", "Warning");
            System.out.println("Unknown Host! " + ex.getMessage());
        }
    }

    public boolean receiveServerInfo(String buffer) {
        String signal = buffer.substring(0, buffer.indexOf(":"));
        if (signal.equals("Rooms*")) {
            String tmp = buffer.substring(buffer.indexOf(":") + 1);
            String rooms[] = tmp.split(":");
            viewClient.roomNameBox.setModel(new javax.swing.DefaultComboBoxModel(rooms));
            return true;
        }
        
        if (signal.equals("Clients*")) {
            String tmp = buffer.substring(buffer.indexOf(":") + 1);
            final String clients[] = tmp.split(":");
            viewClient.jList1.setModel(new javax.swing.AbstractListModel() {
                @Override
                public int getSize() {
                    return clients.length;
                }

                @Override
                public Object getElementAt(int i) {
                    return clients[i];
                }
            });
            return true;
        }
        
        return false;
    }

    public void send(String buffer) {
        out.println(buffer);
    }

    public void receive() {
        new Thread() {

            String buffer;
            String lastUser = "";

            @Override
            public void run() {
                while (true) {
                    try {
                        buffer = in.readLine();
                        if (receiveServerInfo(buffer)) {
                            continue;
                        }
                        String[] message = buffer.split(":", 2);
                        if (lastUser.equals(message[0])) {
                            viewClient.jTextArea1.append(message[1] + "\n");
                        } else {
                            DateFormat dateFormat = new SimpleDateFormat(
                                    "yyyy/MM/dd HH:mm:ss");
                            Calendar cal = Calendar.getInstance();
                            viewClient.jTextArea1.append("Message from "
                                    + message[0].toUpperCase() + " ("
                                    + dateFormat.format(cal.getTime()) + "):\n");
                            viewClient.jTextArea1.append(message[1] + "\n");
                        }
                        lastUser = message[0];
                        System.out.println(buffer);

                    } catch (IOException ex) {
                        viewClient.setIsConnected(false);
                        viewClient.stateTextField.setText("Offline");
                        viewClient.stateTextField.setBackground(new java.awt.Color(255, 102, 102));

                        if ("socketet closed".equals(ex.getMessage())) {
                            messageView.setMessageView("Disconnected!",
                                    "../images/Information-icon.png", "Information");
                            System.out.println(ex.getMessage());
                        } else {
                            messageView.setMessageView("Connection lost!",
                                    "../images/Information-icon.png", "Information");
                            System.out.println(ex.getMessage());
                            viewClient.connectionMenuItem.setEnabled(true);
                            viewClient.disconnectMenuItem.setEnabled(false);
                        }

                        break;
                    }
                }
            }
        }.start();
    }

    public void close() {

        try {
            out.close();
            in.close();
            socket.close();
            viewClient.setIsConnected(false);
        } catch (IOException ex) {
            System.out.println("Disconnection failed!");
        }
    }

    public Socket getSock() {
        return socket;
    }
}
