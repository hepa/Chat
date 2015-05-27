package com.darkfalcon.java.encryption.impl;

import com.darkfalcon.java.encryption.Encrypter;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class RSAEncrypter
        implements Encrypter {

    private KeyPair keyPair;

    public RSAEncrypter() {

    }

    public RSAEncrypter(KeyPair keyPair) {
        this.keyPair = keyPair;
    }

    @Override
    public byte[] encrypt(String plain) {
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(1, this.keyPair.getPublic());
            return cipher.doFinal(plain.getBytes());
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException | InvalidKeyException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public String decrypt(byte[] secret) {
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(2, this.keyPair.getPrivate());
            return new String(cipher.doFinal(secret));
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public byte[] encryptToByte(byte[] plainBytes) {
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(1, this.keyPair.getPublic());
            return cipher.doFinal(plainBytes);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException | InvalidKeyException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public byte[] decryptToByte(byte[] secretBytes) {
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(2, this.keyPair.getPrivate());
            return cipher.doFinal(secretBytes);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
