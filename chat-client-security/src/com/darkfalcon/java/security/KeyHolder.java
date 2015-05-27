/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.darkfalcon.java.security;

import com.darkfalcon.java.keys.AsymmetricKeyUtil;
import com.darkfalcon.java.signature.Signer;
import com.darkfalcon.java.signature.impl.DSASigner;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PublicKey;
import java.security.SignatureException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Darkfalcon
 */
public class KeyHolder {

    private PublicKey publicKeyOfPKI;
    private PublicKey publicKeyOfServer;
    private byte[] signatureOfServer;

    private static KeyHolder INSTANCE;

    public static KeyHolder getInstance() {
        synchronized (KeyHolder.class) {
            if (INSTANCE == null) {
                INSTANCE = new KeyHolder();
            }
            return INSTANCE;
        }
    }

    private KeyHolder() {
    }

    public PublicKey getPublicKeyOfPKI() {
        return publicKeyOfPKI;
    }

    public PublicKey getPublicKeyOfServer() {
        return publicKeyOfServer;
    }

    public byte[] getSignatureOfServer() {
        return signatureOfServer;
    }

    public void setPublicKeyOfPKI(PublicKey publicKeyOfPKI) {
        this.publicKeyOfPKI = publicKeyOfPKI;
    }

    public void setPublicKeyOfServer(PublicKey publicKeyOfServer) {
        this.publicKeyOfServer = publicKeyOfServer;
    }

    public void setSignatureOfServer(byte[] signatureOfServer) {
        this.signatureOfServer = signatureOfServer;
    }

    public boolean isPublicKeyValid() {

        if (publicKeyOfPKI == null || publicKeyOfServer == null || signatureOfServer == null) {
            return false;
        } else {
            Signer signer = new DSASigner(new KeyPair(publicKeyOfPKI, null));
            try {
                return signer.verify(signatureOfServer, AsymmetricKeyUtil.encodePublicKey(publicKeyOfServer));
            } catch (SignatureException | NoSuchProviderException | NoSuchAlgorithmException | InvalidKeyException ex) {
                Logger.getLogger(KeyHolder.class.getName()).log(Level.SEVERE, null, ex);
                throw new RuntimeException(ex);
            }
        }
    }
}
