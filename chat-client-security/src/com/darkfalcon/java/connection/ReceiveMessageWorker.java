/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.darkfalcon.java.connection;

import com.darkfalcon.java.config.ApplicationConfig;
import com.darkfalcon.java.encryption.Encrypter;
import com.darkfalcon.java.forms.ChatForm;
import com.darkfalcon.java.keys.AESKeyUtil;
import com.darkfalcon.java.message.JsonMessage;
import com.darkfalcon.java.message.MessageContainer;
import com.darkfalcon.java.message.MessageType;
import com.darkfalcon.java.message.Opcode;
import com.darkfalcon.java.message.ServerInfo;
import com.darkfalcon.java.message.UserInfo;
import com.darkfalcon.java.messagediggest.MessageDigestUtil;
import com.darkfalcon.java.security.EncryptionProvider;
import com.darkfalcon.java.security.KeyHolder;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import javax.crypto.SecretKey;
import javax.swing.JFrame;
import javax.swing.SwingWorker;
import org.apache.commons.codec.binary.Base64;

/**
 *
 * @author Darkfalcon
 */
public class ReceiveMessageWorker extends SwingWorker<Boolean, MessageContainer> {

    private ChatForm form;
    private Socket connection;
    private BufferedReader inputStream;
    private final PrintWriter outputStream;
    private static final Logger logger = Logger.getLogger(ReceiveMessageWorker.class.getName());
    boolean validated = false;

    public ReceiveMessageWorker(Socket connection, JFrame form) throws IOException {
        this.form = (ChatForm) form;
        this.connection = connection;
        outputStream = new PrintWriter(connection.getOutputStream(), true);
        inputStream = new BufferedReader(new InputStreamReader(connection.getInputStream()));
    }

    @Override
    protected Boolean doInBackground() throws Exception {

        Gson gson = new Gson();
        Base64 base64 = new Base64();
        Encrypter symmetric;

        while (true) {

            String buffer;
            try {
                buffer = inputStream.readLine();
                JsonMessage request = gson.fromJson(buffer, JsonMessage.class);
                JsonMessage response;
                switch (request.getOpcode()) {
                    case CONNECT_SYN_ACK:
                        /* A szerver elkuldte az szerver informaciokat (publikus kulcs, annak az alairasa, szerver neve).
                         * Elkerjuk a publikus kulcs infrastruktura publikus kulcsat, amelyel hitelesitjuk a szerver nyilvanos kulcsat.
                         * Ha ez sikeres, akkor CONNECT_ACK uzenetet kuldunk. 
                         */

                        ServerInfo serverInfo = (ServerInfo) request.getContentObject(ServerInfo.class);
                        KeyHolder keyHolder = KeyHolder.getInstance();
                        keyHolder.setPublicKeyOfServer(serverInfo.getPublicKey());
                        keyHolder.setSignatureOfServer(serverInfo.getSignatureOfPublicKey());
                       
                        publish(new MessageContainer("[CONNECT_SYN_ACK received] - " + serverInfo.getServerName() + " sent its public informations.  ", MessageType.CONSOLE_AREA));
                        logger.info(serverInfo.getServerName() + "'s public key: " + serverInfo.getPublicKey()
                                + ", signature: " + Arrays.toString(serverInfo.getSignatureOfPublicKey()));
                        
                        publish(new MessageContainer("Requesting PKI's public key.", MessageType.CONSOLE_AREA));
                        logger.info("Requesting PKI's public key.");
                        
                        PKIConnection pkiConnection = new PKIConnection(form);
                        keyHolder.setPublicKeyOfPKI(pkiConnection.listen());

                        publish(new MessageContainer("PKI's public key received.", MessageType.CONSOLE_AREA));
                        logger.info("PKI's public key received: " + keyHolder.getPublicKeyOfPKI());

                        EncryptionProvider.getInstance().initAsymmetric(keyHolder.getPublicKeyOfServer());
                        if (keyHolder.isPublicKeyValid()) {
                            response = new JsonMessage();
                            response.setOpcode(Opcode.CONNECT_ACK);
                            SecretKey secretKey = AESKeyUtil.generateKey(AESKeyUtil.SIZE_128);

                            publish(new MessageContainer("Server's public key is valid. Sending generated AES secret key. ", MessageType.CONSOLE_AREA));
                            logger.info("Server's public key is valid.");
                            logger.info("Generated AES key: " + Arrays.toString(secretKey.getEncoded()));

                            /* Kliens oldali szimmetrikus titkositas inicializalasa */
                            EncryptionProvider.getInstance().initSymmetric(secretKey);
                            byte[] encryptedSecretKey = EncryptionProvider.getInstance().getAsymmetricEncryption().encryptToByte(secretKey.getEncoded());
                            response.setContent(base64.encodeAsString(encryptedSecretKey));
                            sendMessage(response, JsonMessage.class);
                        } else {
                            logger.info("Keys validated.");
                            publish(new MessageContainer("Public key is invalid.", MessageType.CONSOLE_AREA));
                            logger.info("Public key is invalid.");
                            break;
                        }
                    case IDENTIFY_SYN:
                        /* A szerver ertesult rola, hogy hitelesitettuk a kulcsat, azonositast ker. Elkuldjuk a 
                         * felhasznalonev, jelszo paros immar titkositva az AES algoritmussal.
                         */
                        publish(new MessageContainer("[IDENTIFY_SYN received] - Server requests identification. ", MessageType.CONSOLE_AREA));
                        logger.info("[IDENTIFY_SYN received] - Server requests identification. ");

                        UserInfo userInfo = new UserInfo();
                        userInfo.setUsername(ApplicationConfig.getConfig().getProperty("username"));
                        byte[] password = MessageDigestUtil.getMessageDigest(ApplicationConfig.getConfig().getProperty("password"));
                        userInfo.setPassword(password);
                        String jsonUserInfo = gson.toJson(userInfo, UserInfo.class);

                        publish(new MessageContainer("Sending username=[" + userInfo.getUsername()
                                + ", password=[" + Arrays.toString(userInfo.getPassword()) + "].", MessageType.CONSOLE_AREA));
                        logger.info("Sending username=[" + userInfo.getUsername() + ", password=[" + Arrays.toString(userInfo.getPassword()) + "].");

                        symmetric = EncryptionProvider.getInstance().getSymmetricEncryption();

                        byte[] secretBytes = symmetric.encrypt(jsonUserInfo);
                        response = new JsonMessage();
                        response.setOpcode(Opcode.IDENTIFY_SYN_ACK);
                        String secret = base64.encodeAsString(secretBytes);
                        logger.info("Userinfo secret: " + secret);
                        response.setContent(secret);
                        sendMessage(response, JsonMessage.class);
                        break;
                    case IDENTIFY_ACK:
                        validated = true;
                        logger.info("Succesful identification.");
                        publish(new MessageContainer("You have successfully logged in.", MessageType.CONSOLE_AREA));
                        form.getSendButton().setEnabled(validated);
                        break;
                    case CONNECTION_REFUSED:
                        logger.info("Connection refused by server! Closing connection.");
                        publish(new MessageContainer("Connection refused by server! Closing connection.", MessageType.CONSOLE_AREA));
                        this.cancel(true);
                        break;
                    case MESSAGE:
                        secret = request.getContent();
                        symmetric = EncryptionProvider.getInstance().getSymmetricEncryption();
                        String message = symmetric.decrypt(base64.decode(secret));
                        publish(new MessageContainer("Message received.", MessageType.CONSOLE_AREA));
                        logger.info("Message received: " + message);
                        publish(new MessageContainer(message, MessageType.MESSAGE_AREA));
                    default:
                        break;
                }
            } catch (IOException ex) {
            }
        }
    }

    private void sendMessage(JsonMessage message, Class type) {
        Gson gson = new Gson();
        String messageText = gson.toJson(message, type);
        System.out.println("sent: " + messageText);
        outputStream.write(messageText + "\n");
        outputStream.flush();
    }

    public void sendMessage(String message, Opcode opcode) {
        JsonMessage jsonMessage = new JsonMessage();
        jsonMessage.setOpcode(opcode);
        Encrypter symmetric = EncryptionProvider.getInstance().getSymmetricEncryption();
        if (message != null) {
            jsonMessage.setContent(new Base64().encodeAsString(symmetric.encrypt(message)));
        }
        sendMessage(jsonMessage, JsonMessage.class);
    }

    @Override
    protected void done() {
        super.done(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void process(List<MessageContainer> chunks) {
        for (MessageContainer chunk : chunks) {
            if (chunk.getMessageType().equals(MessageType.CONSOLE_AREA)) {
                form.appendToConsoleArea(chunk.getMessage());
            }
            if (chunk.getMessageType().equals(MessageType.MESSAGE_AREA)) {

                form.appendToMessageArea(chunk.getMessage());
            }
        }
    }

    public boolean getValidate() {
        return this.validated;
    }
}
