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
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Darkfalcon
 */
public class EncryptionProvider implements SecurityProvider {
    
    private Encrypter asymmetric;
    private Encrypter symmetric;
    private byte[] signature;

    private static EncryptionProvider INSTANCE;
    
    private EncryptionProvider() {
        init();
    }
    
    public EncryptionProvider getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new EncryptionProvider();
        }
        return INSTANCE;
    }
    
    private void init() {
        try {
            String workingDir = System.getProperty("user.dir");
            String pathPriv = workingDir + "\\keys\\private.key";
            String pathPub = workingDir + "\\keys\\public.key";
            String pathSig = workingDir + "\\keys\\public.sig";
           
            PrivateKey privateKey = SecurityFileImporter.importPrivateKey(pathPriv, AsymmetricKeyUtil.ALGORITHM_RSA);
            PublicKey publicKey = SecurityFileImporter.importPublicKey(pathPub, AsymmetricKeyUtil.ALGORITHM_RSA);
            asymmetric = new RSAEncrypter(new KeyPair(publicKey, privateKey));
            this.signature = FileOperationUtil.readFromFile(pathSig);
        } catch (NoSuchAlgorithmException | IOException | InvalidKeySpecException ex) {
            Logger.getLogger(EncryptionProvider.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Failed to init security keys!", ex);
        }
    }
    
    @Override
    public Encrypter getAsymmetricEncryption(int keySize) {
        return this.asymmetric;
    }

    @Override
    public Encrypter getSymmetricEncryption() {
        return this.symmetric;
    }
}
