package com.darkfalcon.java.keys;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AsymmetricKeyUtil {

    public static final String ALGORITHM_RSA = "RSA";
    public static final String ALGORITHM_DSA = "DSA";

    public static final String PROVIDER_SUN = "SUN";

    public static final int DEFAULT_KEY_SIZE = 1024;
    public static final int KEY_SIZE_512 = 512;
    public static final int KEY_SIZE_1024 = 1024;
    public static final int KEY_SIZE_2048 = 2048;
    public static final int KEY_SIZE_4096 = 4096;

    public static KeyPair generateKeyPair(String algorithm) {
        return generateKeyPair(DEFAULT_KEY_SIZE, algorithm, null);
    }

    public static KeyPair generateKeyPair(int keysize, String algorithm) {
        return generateKeyPair(keysize, algorithm, null);
    }

    public static KeyPair generateKeyPair(String algorithm, String provider) {
        return generateKeyPair(DEFAULT_KEY_SIZE, algorithm, provider);
    }

    public static KeyPair generateKeyPair(int keysize, String algorithm, String provider) {
        try {
            KeyPairGenerator keyPairGenerator = null;
            if (provider == null) {
                keyPairGenerator = KeyPairGenerator.getInstance(algorithm);

            } else {
                keyPairGenerator = KeyPairGenerator.getInstance(algorithm, "SUN");
            }
            keyPairGenerator.initialize(keysize, new SecureRandom());
            return keyPairGenerator.generateKeyPair();
        } catch (NoSuchAlgorithmException | NoSuchProviderException ex) {
            Logger.getLogger(AsymmetricKeyUtil.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex);
        }
    }
    
    public static byte[] encodePublicKey(PublicKey publicKey) {
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKey.getEncoded());
        return keySpec.getEncoded();
    }
    
    public static byte[] encodePrivateKey(PrivateKey privateKey) {
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privateKey.getEncoded());
        return keySpec.getEncoded();
    }
    
    public static PublicKey decodePublicKey(byte[] encodedPublicKey, String algorithm) throws NoSuchAlgorithmException, InvalidKeySpecException {
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(encodedPublicKey);
        return KeyFactory.getInstance(algorithm).generatePublic(keySpec);
    }
    
    public static PrivateKey decodePrivateKey(byte[] encodedPrivateKey, String algorithm) throws NoSuchAlgorithmException, InvalidKeySpecException {
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(encodedPrivateKey);
        return KeyFactory.getInstance(algorithm).generatePrivate(keySpec);
    }
}
