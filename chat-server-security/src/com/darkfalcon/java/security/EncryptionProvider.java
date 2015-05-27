/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.darkfalcon.java.security;

import com.darkfalcon.java.encryption.Encrypter;
import com.darkfalcon.java.encryption.impl.RSAEncrypter;
import com.darkfalcon.java.file.util.FileOperationUtil;
import com.darkfalcon.java.file.util.SecurityFileImporter;
import com.darkfalcon.java.keys.AsymmetricKeyUtil;

import java.io.IOException;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.SecretKey;
import security.AESEncrypter;

/**
 *
 * @author Darkfalcon
 */
public class EncryptionProvider implements SecurityProvider {
    
    private Encrypter asymmetric;
    private KeyPair rsaKeyPair;
    private SecretKey aesKey;

    private Encrypter symmetric;
    private byte[] signature;

    private static EncryptionProvider INSTANCE;
    
    private EncryptionProvider() {
        initAsymmetric();
    }
    
    public static EncryptionProvider getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new EncryptionProvider();
        }
        return INSTANCE;
    }
    
    private void initAsymmetric() {
        try {
            String workingDir = System.getProperty("user.dir");
            String pathPriv = workingDir + "\\keys\\private.key";
            String pathPub = workingDir + "\\keys\\public.key";
            String pathSig = workingDir + "\\keys\\public.sig";
           
            PrivateKey privateKey = SecurityFileImporter.importPrivateKey(pathPriv, AsymmetricKeyUtil.ALGORITHM_RSA);
            PublicKey publicKey = SecurityFileImporter.importPublicKey(pathPub, AsymmetricKeyUtil.ALGORITHM_RSA);
            this.rsaKeyPair = new KeyPair(publicKey, privateKey);
            asymmetric = new RSAEncrypter(rsaKeyPair);
            this.signature = FileOperationUtil.readFromFile(pathSig);
        } catch (NoSuchAlgorithmException | IOException | InvalidKeySpecException ex) {
            Logger.getLogger(EncryptionProvider.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Failed to init security keys!", ex);
        }
    }
    
    @Override
    public Encrypter getAsymmetricEncryption() {
        return this.asymmetric;
    }
    
    public KeyPair getAsymmetricKeyPair() {
        return this.rsaKeyPair;
    }
    
    public byte[] getSignatureOfPublicKey() {
        return this.signature;
    }
    
    public SecretKey getSecretKey() {
        return this.aesKey;
    }

    @Override
    public Encrypter getSymmetricEncryption() {
        return this.symmetric;
    }
    
    public void initSymmetric(SecretKey secretKey) {
        this.aesKey = secretKey;
        this.symmetric = new AESEncrypter(secretKey);
    }
    
    public boolean isSymmetricInitialized() {
        return this.symmetric != null;
    }
    
    public boolean isAsymmetricInitialized() {
        return this.asymmetric != null;
    }
}
