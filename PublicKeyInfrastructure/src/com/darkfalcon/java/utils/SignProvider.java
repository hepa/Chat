/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.darkfalcon.java.utils;

import com.darkfalcon.java.file.util.SecurityFileExporter;
import com.darkfalcon.java.file.util.SecurityFileImporter;
import com.darkfalcon.java.keys.AsymmetricKeyUtil;
import com.darkfalcon.java.signature.Signer;
import com.darkfalcon.java.signature.impl.DSASigner;
import java.io.File;
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
public class SignProvider {

    private static SignProvider INSTANCE;
    private Signer signer;
    private KeyPair keyPairOfSigner;

    private SignProvider() {
        init();
    }

    public static SignProvider getInstance() {
        synchronized (SignProvider.class) {
            if (INSTANCE == null) {
                INSTANCE = new SignProvider();
            }
        }
        return INSTANCE;
    }

    private void init() {
        try {
            String workingDir = System.getProperty("user.dir");
            String pathPriv = workingDir + "\\config\\dsakeys\\dsa_private.key";
            String pathPub = workingDir + "\\config\\dsakeys\\dsa_public.key";
            File filePriv = new File(pathPriv);
            File filePub = new File(pathPub);

            if (filePriv.exists() && filePub.exists()) {
                PrivateKey privateKey = SecurityFileImporter.importPrivateKey(pathPriv, AsymmetricKeyUtil.ALGORITHM_DSA);
                PublicKey publicKey = SecurityFileImporter.importPublicKey(pathPub, AsymmetricKeyUtil.ALGORITHM_DSA);
                this.keyPairOfSigner = new KeyPair(publicKey, privateKey);
                signer = new DSASigner(keyPairOfSigner);
                Logger.getLogger(SignProvider.class.getName()).log(Level.INFO, "DSA Keypair exists!");
            } else {
                this.keyPairOfSigner = AsymmetricKeyUtil.generateKeyPair(AsymmetricKeyUtil.KEY_SIZE_1024,
                        AsymmetricKeyUtil.ALGORITHM_DSA);
                signer = new DSASigner(keyPairOfSigner);
                SecurityFileExporter.exportPrivateKey(pathPriv, keyPairOfSigner.getPrivate());
                SecurityFileExporter.exportPublicKey(pathPub, keyPairOfSigner.getPublic());
                Logger.getLogger(SignProvider.class.getName()).log(Level.INFO, "DSA Keypair generated!");
            }
        } catch (NoSuchAlgorithmException | IOException | InvalidKeySpecException ex) {
            Logger.getLogger(SignProvider.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Failed to init security keys!", ex);
        }
    }

    public Signer getSigner() {
        return this.signer;
    }
    
    public KeyPair getKeyPairOfSigner() {
        return this.keyPairOfSigner;
    }
}
