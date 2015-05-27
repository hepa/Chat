/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.darkfalcon.java.execution;

import com.darkfalcon.java.config.ApplicationConfig;
import com.darkfalcon.java.encryption.Encrypter;
import com.darkfalcon.java.encryption.impl.RSAEncrypter;
import com.darkfalcon.java.forms.ChatForm;
import com.darkfalcon.java.keys.AESKeyUtil;
import com.darkfalcon.java.keys.AsymmetricKeyUtil;
import com.darkfalcon.java.message.JsonMessage;
import com.darkfalcon.java.message.Opcode;
import com.darkfalcon.java.message.UserInfo;
import com.darkfalcon.java.messagediggest.MessageDigestUtil;
import com.darkfalcon.java.security.EncryptionProvider;
import com.google.gson.Gson;
import java.security.KeyPair;
import java.util.Arrays;
import javax.crypto.SecretKey;
import org.apache.commons.codec.binary.Base64;

/**
 *
 * @author Darkfalcon
 */
public class Main {

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ChatForm().setVisible(true);
            }
        });
        
        /*UserInfo userInfo = new UserInfo();
        userInfo.setUsername("darkfalconsfdfssfadfsdfs");
        byte[] password = MessageDigestUtil.getMessageDigest("passwordsfdfsdafsad");
        System.out.println("pass: " + Arrays.toString(password));
        userInfo.setPassword(password);
        
        KeyPair keyPair = AsymmetricKeyUtil.generateKeyPair(AsymmetricKeyUtil.ALGORITHM_RSA);
        
        
        Gson gson = new Gson();
        String jsonUserInfo = gson.toJson(userInfo);
        System.out.println(jsonUserInfo);
        EncryptionProvider encryptionProvider = EncryptionProvider.getInstance();
        encryptionProvider.initAsymmetric(keyPair.getPublic());
        
        JsonMessage jsonMessage = new JsonMessage();
        
        if (encryptionProvider.isAsymmetricInitialized()) {
            Encrypter encrypter = encryptionProvider.getAsymmetricEncryption();
            byte[] encrypted = encrypter.encrypt(jsonUserInfo);
            System.out.println("Encrypted: " + Arrays.toString(encrypted));
            String content = new Base64().encodeAsString(encrypted);
            jsonMessage.setOpcode(Opcode.CONNECT_ACK);
            jsonMessage.setContent(content);
            
            String message = gson.toJson(jsonMessage);
            
            
            JsonMessage jsonM = gson.fromJson(message, JsonMessage.class);
            System.out.println(jsonM.getOpcode());
            byte[] encryptedM = new Base64().decode(jsonM.getContent());
            Encrypter enc = new RSAEncrypter(keyPair);
            String con = enc.decrypt(encryptedM);
            UserInfo u = gson.fromJson(con, UserInfo.class);

            System.out.println(u.getPassword());
            System.out.println(u.getUsername());
        } else {
             
        }*/
        
        /*encryptionProvider.initSymmetric(key);
        Encrypter sim = encryptionProvider.getSymmetricEncryption();
        byte[] secret = sim.encrypt(jsonUserInfo);
        System.out.println(Arrays.toString(secret));
        String r = sim.decrypt(secret);
        UserInfo info = gson.fromJson(r, UserInfo.class);
        System.out.println(Arrays.toString(info.getPassword()));
        System.out.println(info.getUsername());*/
    }
    
    

}
