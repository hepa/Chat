/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.darkfalcon.java.signature;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SignatureException;

/**
 *
 * @author Darkfalcon
 */
public interface Signer {

    public byte[] sign(byte[] dataToSign) throws SignatureException, NoSuchProviderException, NoSuchAlgorithmException, InvalidKeyException;

    public boolean verify(byte[] signedData, byte[] dataToVerify) throws SignatureException, NoSuchProviderException, NoSuchAlgorithmException, InvalidKeyException;
}
