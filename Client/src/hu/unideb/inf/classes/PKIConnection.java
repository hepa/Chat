/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.unideb.inf.classes;

import com.darkfalcon.java.config.ApplicationConfig;
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
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.codec.binary.Base64;

/**
 *
 * @author Darkfalcon
 */
public class PKIConnection {

    private Socket connection;
    private String pkiAddress;
    private int pkiPort;
    private PrintWriter outputStream;
    private BufferedReader inputStream;

    public PKIConnection() {
        try {
            pkiAddress = ApplicationConfig.getConfig().getProperty("pki-address");
            pkiPort = Integer.parseInt(ApplicationConfig.getConfig().getProperty("pki-port"));
            connection = new Socket(pkiAddress, pkiPort);
            outputStream = new PrintWriter(connection.getOutputStream(), true);
            inputStream = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            listen();
        } catch (IOException ex) {
            Logger.getLogger(PKIConnection.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex);
        }
    }

    private void listen() {
        new Thread() {

            String buffer;
            String lastUser = "";

            @Override
            public void run() {
                while (true) {
                    try {
                        buffer = inputStream.readLine();
                        Gson gson = new Gson();
                        Message<String> response = gson.fromJson(buffer, new TypeToken<Message<String>>() {
                        }.getType());
                        switch (response.getOpcode()) {
                            case VALIDATE_ACK:
                                byte[] dec = new Base64().decode(response.getContent());
                                PublicKey publicKey
                                        = AsymmetricKeyUtil.decodePublicKey(dec, AsymmetricKeyUtil.ALGORITHM_RSA);
                                System.out.println(publicKey);
                                Message<String> closeMessage = new Message<String>();
                                closeMessage.setOpcode(Opcode.VALIDATE_CLOSE);
                                send(closeMessage);
                                close();
                                return;
                            default:
                                break;
                        }
                    } catch (IOException ex) {
                        Logger.getLogger(PKIConnection.class.getName()).log(Level.SEVERE, null, ex);
                        throw new RuntimeException(ex);
                    } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
                        Logger.getLogger(PKIConnection.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }.start();
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
            System.out.println("Connection Closed!");
        } catch (IOException ex) {
            Logger.getLogger(PKIConnection.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex);
        }
    }

    public Socket getConnecton() {
        return connection;
    }
}
