/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.darkfalcon.java.encryption;

import com.darkfalcon.java.security.SecurityProviderImpl;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SignatureException;
import java.util.Arrays;
import com.darkfalcon.java.keys.AsymmetricKeyUtil;

/**
 *
 * @author Darkfalcon
 */
public class Main {
    public static void main(String[] args) throws SignatureException, NoSuchProviderException, NoSuchAlgorithmException, InvalidKeyException {
        /*EncryptionFacility.init(new SecurityProviderImpl());
        byte[] secret = EncryptionFacility.getAsymmetric().encrypt("kutya");
        System.out.println(EncryptionFacility.getAsymmetric().decrypt(secret));
        
        secret = EncryptionFacility.getSymmetric().encrypt("macska");
        System.out.println(EncryptionFacility.getSymmetric().decrypt(secret));
        
        KeyPair rsaKeypair = AsymmetricKeyUtil.generateKeyPair(AsymmetricKeyUtil.KEY_SIZE_2048, AsymmetricKeyUtil.ALGORITHM_RSA);
        byte[] encoded = EncryptionFacility.getSigner().sign(rsaKeypair.getPublic().getEncoded());
        System.out.println(Arrays.toString(encoded));
        System.out.println(EncryptionFacility.getSigner().verify(encoded, rsaKeypair.getPublic().getEncoded()));*/
    }
}
