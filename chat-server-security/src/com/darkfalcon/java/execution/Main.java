/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.darkfalcon.java.execution;

import com.darkfalcon.java.config.ApplicationConfig;
import com.darkfalcon.java.encryption.Encrypter;
import com.darkfalcon.java.forms.ServerForm;
import com.darkfalcon.java.keys.AESKeyUtil;
import com.darkfalcon.java.message.JsonMessage;
import com.darkfalcon.java.message.Opcode;
import com.darkfalcon.java.message.UserInfo;
import com.darkfalcon.java.messagediggest.MessageDigestUtil;
import com.darkfalcon.java.security.EncryptionProvider;
import com.google.gson.Gson;
import java.util.Arrays;
import javax.crypto.SecretKey;
import org.apache.commons.codec.binary.Base64;

/**
 *
 * @author Darkfalcon
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ServerForm().setVisible(true);
            }
        });
        
        
        /*JsonMessage response;
        Gson gson = new Gson();
        
        UserInfo userInfo = new UserInfo();
                            userInfo.setUsername("macsaka");
                            byte[] password = MessageDigestUtil.getMessageDigest("kutya");
                            userInfo.setPassword(password);
                            String jsonUserInfo = gson.toJson(userInfo, UserInfo.class);
                            
                            SecretKey secretKey = AESKeyUtil.generateKey();
                            EncryptionProvider prov = EncryptionProvider.getInstance();
                            prov.initSymmetric(secretKey);
                            Encrypter encrypter = EncryptionProvider.getInstance().getSymmetricEncryption();
                            
                            byte[] secret = encrypter.encrypt(jsonUserInfo);
                            String value = new Base64().encodeAsString(secret);
                            
                            String key = new Base64().encodeAsString(secretKey.getEncoded());
                            SecretKey secretKey2 = AESKeyUtil.generateSecretKeyFromBytes(new Base64().decode(key));
                            
                            prov.initSymmetric(secretKey2);
                            
                            
                                System.out.println("secret: " + secret);
                                byte[] decodedSecret = new Base64().decode(value);
                                System.out.println("Decoded: " + Arrays.toString(decodedSecret));
                                System.out.println(EncryptionProvider.getInstance().isSymmetricInitialized());
                                System.out.println(EncryptionProvider.getInstance().getSecretKey());
                                String jsonUserInfos = encrypter.decrypt(decodedSecret);
                                UserInfo userInfos = (UserInfo) gson.fromJson(jsonUserInfos, UserInfo.class);
                                System.out.println(userInfos.getPassword());
                                System.out.println(userInfos.getUsername());
        
        
        
        byte[] m = prov.getSymmetricEncryption().encrypt("kutya");
        System.out.println(Arrays.toString(m));
        
        
     */   
    }

}
