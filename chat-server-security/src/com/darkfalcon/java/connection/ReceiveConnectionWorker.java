/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.darkfalcon.java.connection;

import com.darkfalcon.java.config.ApplicationConfig;
import com.darkfalcon.java.forms.ServerForm;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.SwingWorker;

/**
 *
 * @author Darkfalcon
 */
public class ReceiveConnectionWorker extends SwingWorker<Boolean, String> {

    private ServerForm form;
    private ServerSocket serverSocket;
    private static final Logger log = Logger.getLogger(ReceiveConnectionWorker.class.getName());

    public ReceiveConnectionWorker(JFrame form) {
        this.form = (ServerForm) form;
    }

    @Override
    protected Boolean doInBackground() throws Exception {
        
        publish("Waiting for connections...");
        
        int listenPort = Integer.parseInt(ApplicationConfig.getConfig().getProperty("listen-port"));
        serverSocket = new ServerSocket(listenPort);
        Socket socket;
        
        while (true) {
            try {
                socket = serverSocket.accept();
                publish("Connection request from: " + socket.getInetAddress() + ":" + socket.getPort() + ".\n");
                new ConnectionHandler(socket, form).execute();
            } catch (IOException ex) {
                //Logger.getLogger(ConnectionHandler.class.getName()).log(Level.SEVERE, null, ex);
                //throw new RuntimeException(ex);
            }
        }

    }

    @Override
    protected void done() {
        super.done(); //To change body of generated methods, choose Tools | Templates.
        System.out.println("Done");
    }

    @Override
    protected void process(List<String> chunks) {
        String message = chunks.get(chunks.size() - 1);
        form.appendToConsole(message);
    }
}
