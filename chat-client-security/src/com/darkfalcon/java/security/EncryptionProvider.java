/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.darkfalcon.java.security;

import com.darkfalcon.java.encryption.Encrypter;
import com.darkfalcon.java.encryption.impl.RSAEncrypter;
import com.darkfalcon.java.file.util.SecurityFileImporter;
import com.darkfalcon.java.keys.AsymmetricKeyUtil;
import java.io.IOException;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
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
    private SecretKey aesSecret;

    private Encrypter symmetric;
    private byte[] signature;

    private static EncryptionProvider INSTANCE;
    
    public static EncryptionProvider getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new EncryptionProvider();
        }
        return INSTANCE;
    }
    
    public void initAsymmetric(PublicKey publicKey) {
        this.rsaKeyPair = new KeyPair(publicKey, null);
        asymmetric = new RSAEncrypter(rsaKeyPair);
    }
    
    public void initSymmetric(SecretKey secretKey) {
        this.aesSecret = secretKey;
        symmetric = new AESEncrypter(secretKey);
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

    @Override
    public Encrypter getSymmetricEncryption() {
        return this.symmetric;
    }
    
    public boolean isAsymmetricInitialized() {
        return asymmetric != null;
    }
    
    public boolean isSymmetricInitialized() {
        return symmetric != null;
    }
}
