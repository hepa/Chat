/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.darkfalcon.java.connection;

import com.darkfalcon.java.config.ApplicationConfig;
import com.darkfalcon.java.encryption.Encrypter;
import com.darkfalcon.java.forms.ServerForm;
import com.darkfalcon.java.keys.AESKeyUtil;
import com.darkfalcon.java.keys.AsymmetricKeyUtil;
import com.darkfalcon.java.message.JsonMessage;
import com.darkfalcon.java.message.Opcode;
import com.darkfalcon.java.message.ServerInfo;
import com.darkfalcon.java.message.UserInfo;
import com.darkfalcon.java.security.EncryptionProvider;
import com.darkfalcon.java.services.ServiceProvider;
import com.darkfalcon.java.services.UserService;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.security.PublicKey;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.SecretKey;
import javax.swing.JFrame;
import javax.swing.SwingWorker;
import org.apache.commons.codec.binary.Base64;

/**
 *
 * @author Darkfalcon
 */
public class ConnectionHandler extends SwingWorker<Boolean, String> {

    private BufferedReader inputStream;
    private PrintWriter outputStream;
    private UserService userService;
    private Socket connection;
    private ServerForm form;
    private static final Logger logger = Logger.getLogger(ConnectionHandler.class.getName());

    public ConnectionHandler(final Socket connection, JFrame form) throws IOException {
        this.connection = connection;
        this.form = (ServerForm) form;
        inputStream = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        outputStream = new PrintWriter(connection.getOutputStream(), true);
        userService = ServiceProvider.getUserService();
    }

    @Override
    protected Boolean doInBackground() throws Exception {
        boolean validated = false;
        
        publish("Connection accepted!");
        Gson gson = new Gson();
        
        JsonMessage response;
        String secret;
        Encrypter symmetric;
        
        Base64 base64 = new Base64();
        
        while (true) {

            try {
                String buffer = inputStream.readLine();
                JsonMessage request = gson.fromJson(buffer, JsonMessage.class);
                
                System.out.println("BUffer" + buffer);
                
                switch (request.getOpcode()) {
                    case CONNECT_SYN:
                        /* 
                         * A kliens kapcsolodasi kerelmet (CONNECT_SYN) kuldott.
                         * A szerver informaciok (publikus kulcs, annak az alairasa, a server neve) kuldese a kliensnak.
                         * Erre CONNECT_SYN_ACK uzenetet kuldunk.
                         */
                        PublicKey publicKey = EncryptionProvider.getInstance().getAsymmetricKeyPair().getPublic();
                        byte[] signature = EncryptionProvider.getInstance().getSignatureOfPublicKey();
                        String serverName = ApplicationConfig.getConfig().getProperty("server-name");
                        ServerInfo serverInfo = new ServerInfo(serverName, AsymmetricKeyUtil.ALGORITHM_RSA, publicKey, signature);

                        publish("[CONNECT_SYN received] - Client requested server information.  ");
                        logger.info("Public key: " + publicKey);
                        logger.info("Signature: " + Arrays.toString(signature));
                        logger.info("Server name: " + serverName);

                        response = new JsonMessage();
                        response.setOpcode(Opcode.CONNECT_SYN_ACK);
                        response.setContentObject(serverInfo, ServerInfo.class);
                        sendMessage(response, JsonMessage.class);
                        break;

                    case CONNECT_ACK:
                        /* 
                         * A kliens ellenorizte a kulcsunk hitelesseget. (CONNECT_ACK) kuldott.
                         * Egyben elkuldott nekunk egy AES kulcsot, amelyet a szerver publikus kulcsaval titkositott.
                         * Ezt mi visszafejtjuk a titkos kulcsunkkal, majd kerjuk, hogy kuldje el a felhasznalonev - jelszo parost.
                         * Innentol az uzeneteket titkositjuk.
                         */
                        byte[] encrypterSecretKey = base64.decode(request.getContent());
                        byte[] secretKeyAsBytes = EncryptionProvider.getInstance().getAsymmetricEncryption().decryptToByte(encrypterSecretKey);
                        SecretKey secretKey = AESKeyUtil.generateSecretKeyFromBytes(secretKeyAsBytes);

                        publish("[CONNECT_ACK received] - Client validated public key. Secret key for AES encryption received. ");
                        logger.info("AES key received: " + Arrays.toString(secretKey.getEncoded()));

                        EncryptionProvider.getInstance().initSymmetric(secretKey);
                        response = new JsonMessage();
                        response.setOpcode(Opcode.IDENTIFY_SYN);
                        sendMessage(response, JsonMessage.class);
                        break;

                    case IDENTIFY_SYN_ACK:
                        /*
                         * A kliens elkuldte az azositashoz szukseges adatokat (felhasznalonev, jelszo).
                         * Ez ellenorizzuk, ha megfelelo, akkor egy IDENTIFY_ACK uzenettel valaszolunk, ha nem,
                         * akkor CONNECTION_REFUSED uzenetet kuldunk, majd bontjuk a kapcsolatot, a szal leall.
                         */
                        secret = request.getContent();
                        byte[] decodedSecret = base64.decode(secret);
                        symmetric = EncryptionProvider.getInstance().getSymmetricEncryption();
                        String jsonUserInfo = symmetric.decrypt(decodedSecret);

                        UserInfo userInfo = (UserInfo) gson.fromJson(jsonUserInfo, UserInfo.class);
                        validated = userService.validateUser(userInfo.getUsername(), userInfo.getPassword());

                        logger.info("Secret received: " + secret);
                        logger.info("Client sent identification data: " + "username=[" + userInfo.getUsername() + "] - password=[" + Arrays.toString(userInfo.getPassword()) + "].");

                        publish("[IDENTIFY_SYN_ACK received] - Client sent identification data: "
                                + "username=[" + userInfo.getUsername() + "] - password=[" + Arrays.toString(userInfo.getPassword()) + "].");
                        
                        response = new JsonMessage();
                        if (validated) {
                            response.setOpcode(Opcode.IDENTIFY_ACK);
                            logger.info("Client verified. ");
                            publish("Client verified.");
                        } else {
                            response.setOpcode(Opcode.CONNECTION_REFUSED);
                            String errorMessage = "Invalid username or password!";
                            response.setContent(base64.encodeAsString(symmetric.encrypt(errorMessage)));
                            logger.info("Client identification failed! Connection will be closed! ");
                            publish("Client identification failed! Connection will be closed!");  
                        }
                        sendMessage(response, JsonMessage.class);
                        break;
                    case MESSAGE:
                        /* A kliens uzenetet kuldott. Egybol visszakuldjuk. */
                        secret = request.getContent();
                        symmetric = EncryptionProvider.getInstance().getSymmetricEncryption();
                        String message = symmetric.decrypt(base64.decode(secret));
                        
                        logger.info("Message received(secret): " + secret);
                        logger.info("Message received(plain): " + message);
                        publish("Message received: " + message);
                        
                        secret = base64.encodeAsString(symmetric.encrypt(message));
                        
                        response = new JsonMessage();
                        response.setOpcode(Opcode.MESSAGE);
                        response.setContent(secret);
                        sendMessage(response, JsonMessage.class);
                        break;
                    default:
                        logger.warning("Invalid opcode: " + request.getOpcode());
                        break;
                }

                if (buffer == null) {
                    System.out.println("Disconnected");
                    break;
                }

                //outputStream.println(buffer);
            } catch (IOException ex) {
                logger.log(Level.SEVERE, ex.getMessage(), ex);
            }
        }
        return true;
    }
    
    private JsonMessage createEncryptedJsonMessage(Opcode opcode, String message) {
        return null;
    }

    private void sendMessage(JsonMessage message, Class type) {
        Gson gson = new Gson();
        String messageText = gson.toJson(message, type);
        outputStream.write(messageText + "\n");
        outputStream.flush();
    }

    public void close() {

        try {
            outputStream.close();
            inputStream.close();
            connection.close();
            form.appendToConsole("Connection closed!");
        } catch (IOException ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
            throw new RuntimeException(ex);
        }
    }
    
    @Override
    protected void done() {
        super.done(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void process(List<String> chunks) {
        String message = chunks.get(chunks.size() - 1);

        form.appendToConsole(message);

    }
}
