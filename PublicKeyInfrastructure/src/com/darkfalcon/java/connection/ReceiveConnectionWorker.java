/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.darkfalcon.java.connection;

import com.darkfalcon.java.config.ApplicationConfig;
import com.darkfalcon.java.forms.MainFrame;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.SwingWorker;

/**
 *
 * @author Darkfalcon
 */
public class ReceiveConnectionWorker extends SwingWorker<Boolean, String> {

    private MainFrame form;
    private ServerSocket serverSocket;

    public ReceiveConnectionWorker(JFrame form) {
        this.form = (MainFrame) form;
    }

    @Override
    protected Boolean doInBackground() throws Exception {
        
        System.out.println("Doing in background");
        
        int listenPort = Integer.parseInt(ApplicationConfig.getConfig().getProperty("listen-port"));
        serverSocket = new ServerSocket(listenPort);
        Socket socket;
        
        while (true) {
            try {
                socket = serverSocket.accept();
                System.out.println("Accept connection");
                String message = "Connection from: " + socket.getInetAddress() + ":" + socket.getPort() + ".\n";
                publish(message);
                new ConnectionHandler(socket, form).handleConnection();
            } catch (IOException ex) {
                //Logger.getLogger(ConnectionHandler.class.getName()).log(Level.SEVERE, null, ex);
                //throw new RuntimeException(ex);
            }
        }

    }

    @Override
    protected void done() {
        super.done(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void process(List<String> chunks) {
        String message = chunks.get(chunks.size() - 1);
        form.appendToConsoleArea(message);
    }

}
