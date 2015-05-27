/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.darkfalcon.java.connection;

import com.darkfalcon.java.config.ApplicationConfig;
import com.darkfalcon.java.forms.ChatForm;
import com.darkfalcon.java.keys.AsymmetricKeyUtil;
import com.darkfalcon.java.message.Message;
import com.darkfalcon.java.message.Opcode;
import com.darkfalcon.java.security.KeyHolder;
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
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
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
    private ChatForm form;

    public PKIConnection(JFrame form) {
        try {
            this.form = (ChatForm) form;
            pkiAddress = ApplicationConfig.getConfig().getProperty("pki-address");
            pkiPort = Integer.parseInt(ApplicationConfig.getConfig().getProperty("pki-port"));
            connection = new Socket(pkiAddress, pkiPort);
        } catch (IOException ex) {
            Logger.getLogger(PKIConnection.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex);
        }
    }

    public PublicKey listen() {
        ExecutorService service = Executors.newSingleThreadExecutor();
        form.appendToConsoleArea("Try to obtain PKI's public key!");
        Future<PublicKey> future = service.submit(new ReceivePKIInfoWorker(connection));
        PublicKey publicKeyOfPKI;
        try {
            publicKeyOfPKI = future.get();
            KeyHolder.getInstance().setPublicKeyOfPKI(publicKeyOfPKI);
            form.appendToConsoleArea("Succefully obtained PKI's public key!");
            return publicKeyOfPKI;
        } catch (InterruptedException | ExecutionException ex) {
            Logger.getLogger(PKIConnection.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Cannot obtain PKI public key!", ex);
        }    
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

    public Socket getConnecton() {
        return connection;
    }
}
