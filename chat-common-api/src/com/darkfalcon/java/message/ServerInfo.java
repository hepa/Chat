/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.darkfalcon.java.message;

import com.darkfalcon.java.keys.AsymmetricKeyUtil;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;

/**
 *
 * @author Darkfalcon
 */
public class ServerInfo {
    
    private String serverName;
    private String algorithm;
    private byte[] publicKeyOfServer;
    private byte[] signatureOfPublicKey;
    
    public ServerInfo() {}

    public ServerInfo(String serverName, String algorithm, PublicKey publicKeyOfServer, byte[] signatureOfPublicKey) {
        this.serverName = serverName;
        this.algorithm = algorithm;
        this.publicKeyOfServer = AsymmetricKeyUtil.encodePublicKey(publicKeyOfServer);
        this.signatureOfPublicKey = signatureOfPublicKey;
    }

    public String getServerName() {
        return serverName;
    }

    public String getAlgorithm() {
        return algorithm;
    }

    public byte[] getPublicKeyOfServer() {
        return publicKeyOfServer;
    }

    public byte[] getSignatureOfPublicKey() {
        return signatureOfPublicKey;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }

    public void setPublicKeyOfServer(byte[] publicKeyOfServer) {
        this.publicKeyOfServer = publicKeyOfServer;
    }

    public void setSignatureOfPublicKey(byte[] signatureOfPublicKey) {
        this.signatureOfPublicKey = signatureOfPublicKey;
    }
  
    public PublicKey getPublicKey() throws NoSuchAlgorithmException, InvalidKeySpecException {
        return AsymmetricKeyUtil.decodePublicKey(publicKeyOfServer, algorithm);
    }
    
    public void setPublicKey(PublicKey publicKeyOfServer) {
        this.publicKeyOfServer = AsymmetricKeyUtil.encodePublicKey(publicKeyOfServer);
    }
}
