/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.darkfalcon.java.connection;

import com.darkfalcon.java.keys.AsymmetricKeyUtil;
import com.darkfalcon.java.message.Message;
import com.darkfalcon.java.message.Opcode;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.security.PublicKey;
import java.util.concurrent.Callable;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.codec.binary.Base64;

/**
 *
 * @author Darkfalcon
 */
public class ReceivePKIInfoWorker implements Callable {

    private final PrintWriter outputStream;
    private final BufferedReader inputStream;
    private final Socket connection;

    public ReceivePKIInfoWorker(final Socket connection) {
        try {
            this.connection = connection;
            outputStream = new PrintWriter(connection.getOutputStream(), true);
            inputStream = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        } catch (IOException ex) {
            Logger.getLogger(PKIConnection.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex);
        }
    }

    @Override
    public Object call() throws Exception {
        
        System.out.println("Running...");

        PublicKey publicKeyOfPKI = null;

        String buffer;
        
        boolean sent = false;

        while (true) {
            try {
                if (!sent) {
                    sendRequest();
                    sent = true;
                } 
                buffer = inputStream.readLine();
                Gson gson = new Gson();
                Message<String> response = gson.fromJson(buffer, new TypeToken<Message<String>>() {
                }.getType());
                switch (response.getOpcode()) {
                    case VALIDATE_ACK:
                        byte[] dec = new Base64().decode(response.getContent());
                        publicKeyOfPKI = AsymmetricKeyUtil.decodePublicKey(dec, AsymmetricKeyUtil.ALGORITHM_DSA);
                        System.out.println(publicKeyOfPKI);
                        Message<String> closeMessage = new Message<String>();
                        closeMessage.setOpcode(Opcode.VALIDATE_CLOSE);
                        send(closeMessage);
                        close();
                        Thread.currentThread().join(2000);
                        return publicKeyOfPKI;
                    default:
                        ;
                }
            } catch (IOException ex) {
                Logger.getLogger(PKIConnection.class.getName()).log(Level.SEVERE, null, ex);
                throw new RuntimeException(ex);
            } 
        }
    }
    
    private void sendRequest() {
        Gson gson = new Gson();
        Message<String> message = new Message<String>();
        message.setOpcode(Opcode.VALIDATE_SYN);
        String messageText = gson.toJson(message, new TypeToken<Message<String>>() {}.getType());
        send(messageText);
    }
    
    public void send(Message message) {
        Gson gson = new Gson();
        String messageText = gson.toJson(message);
        send(messageText);
    }

    public void send(String buffer) {
        outputStream.write(buffer + "\n");
        outputStream.flush();
    }

    public void close() {

        try {
            outputStream.close();
            inputStream.close();
            connection.close();
        } catch (IOException ex) {
            Logger.getLogger(PKIConnection.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex);
        }
    }

}
