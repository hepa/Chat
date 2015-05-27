/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.darkfalcon.java.connection;

/**
 *
 * @author Darkfalcon
 */
import com.darkfalcon.java.config.ApplicationConfig;
import com.darkfalcon.java.forms.ChatForm;
import com.darkfalcon.java.message.Opcode;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import javax.swing.JFrame;

public class Connection {

    private Socket connection;
    private PrintWriter output;
    private BufferedReader input;
    private ChatForm form;
    private ReceiveMessageWorker worker;

    public Connection(final JFrame form) {
        this.form = (ChatForm) form;
    }
    
    public void connect() {
        String address = ApplicationConfig.getConfig().getProperty("chat-server-address");
        String port = ApplicationConfig.getConfig().getProperty("chat-server-port");
        
        try {
            connection = new Socket(address, Integer.parseInt(port));
            output = new PrintWriter(connection.getOutputStream(), true);
            input = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            
            this.form.appendToConsoleArea("Connected to server: " + address + ":" + port);
            
            startReceive();
        } catch (IOException ex) {
            this.form.appendToConsoleArea("Unknown Host: " + address + ":" + port);
        }
    }

    private void startReceive() throws IOException {
        worker = new ReceiveMessageWorker(connection ,form);
        worker.execute();
    }
    
    public void sendMessage(String message) {
        sendMessage(message, Opcode.MESSAGE);
    }
    
    public void sendMessage(String message, Opcode opcode) {
        worker.sendMessage(message, opcode);
    }

    public void close() {

        try {
            output.close();
            input.close();
            connection.close();
            form.appendToConsoleArea("Disconnected");
        } catch (IOException ex) {
            form.appendToConsoleArea("Disconnection failed!" + ex.getMessage());
        }
    }
    
    public boolean isConnected() {
        return connection.isConnected();
    }
}
