/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.darkfalcon.java.connection;

import com.darkfalcon.java.entity.server.KeyRegistry;
import com.darkfalcon.java.message.Message;
import com.darkfalcon.java.message.Opcode;
import com.darkfalcon.java.services.SecurityService;
import com.darkfalcon.java.services.ServiceProvider;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.security.KeyPair;
import java.security.PublicKey;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.codec.binary.Base64;

/**
 *
 * @author Darkfalcon
 */
public class ConnectionHandler {

    private BufferedReader inputStream;
    private PrintWriter outputStream;
    private SecurityService service;
    private Socket connection;

    public ConnectionHandler(final Socket connection) throws IOException {
        this.connection = connection;
        inputStream = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        outputStream = new PrintWriter(connection.getOutputStream(), true);
        service = ServiceProvider.getSecurityService();
    }

    public void handleConnection() throws IOException {

        new Thread() {

            @Override
            public void run() {

                System.out.println("Connection thread");
                Gson gson = new Gson();

                while (true) {

                    try {
                        String buffer = inputStream.readLine();
                        Message<String> request = gson.fromJson(buffer, new TypeToken<Message<String>>() {}.getType());
                        
                        switch (request.getOpcode()) {
                            case VALIDATE_SYN:
                                PublicKey publicKey = getPublicKeyOfServer(request.getContent());
                                System.out.println(publicKey);
                                String key = new Base64().encodeAsString(publicKey.getEncoded());
                                Message<String> response = new Message<>();
                                response.setOpcode(Opcode.VALIDATE_ACK);
                                response.setContent(key);
                                sendMessage(response);
                                break;
                            case VALIDATE_CLOSE:
                                inputStream.close();
                                outputStream.close();
                                connection.close();
                                return;
                            default:
                                Logger.getLogger(ConnectionHandler.class.getName()).log(Level.WARNING, null,
                                        "Invalid opcode: " + request.getOpcode());
                                break;
                        }

                        if (buffer == null) {
                            System.out.println("Disconnected");
                            break;
                        }

                        outputStream.println(buffer);

                    } catch (IOException ex) {
                        Logger.getLogger(ConnectionHandler.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            
            private void sendMessage(Message message) {
                Gson gson = new Gson();
                String messageText = gson.toJson(message);
                outputStream.write(messageText + "\n");
                outputStream.flush();
            }
            
            private PublicKey getPublicKeyOfServer(String serverId) {
                return service.getKeyRegistryByServerId(serverId).getKeyPair().getPublic();
            }
        }.start();    
    }
    
    public void close() {

        try {
            outputStream.close();
            inputStream.close();
            connection.close();
            System.out.println("Connection closed!");
        } catch (IOException ex) {
            Logger.getLogger(ConnectionHandler.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex);
        }
    }
    
}
