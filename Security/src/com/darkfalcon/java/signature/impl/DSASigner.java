/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.darkfalcon.java.signature.impl;

import com.darkfalcon.java.signature.Signer;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Signature;
import java.security.SignatureException;

/**
 *
 * @author Darkfalcon
 */
public class DSASigner implements Signer {
    
    private KeyPair keyPair;
    
    public DSASigner(KeyPair keyPair) {
        this.keyPair = keyPair;
    }

    @Override
    public byte[] sign(byte[] dataToSign) throws SignatureException, NoSuchProviderException, NoSuchAlgorithmException, InvalidKeyException {
        Signature signature = Signature.getInstance("DSA", "SUN");
        signature.initSign(keyPair.getPrivate());
	signature.update(dataToSign);
	return signature.sign();
    }

    @Override
    public boolean verify(byte[] signedData, byte[] dataToVerify) throws SignatureException, NoSuchProviderException, NoSuchAlgorithmException, InvalidKeyException {
        Signature signature = Signature.getInstance("DSA", "SUN");
        signature.initVerify(keyPair.getPublic());
        signature.update(dataToVerify);
        return signature.verify(signedData);
    }
    
}
