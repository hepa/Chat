package com.darkfalcon.java.keys;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class AESKeyUtil {

    private static final String password = "test";
    private static int pswdIterations = 65536;
    private static int keySize = 128;

    public static SecretKey generateKey() {
        try {
            SecureRandom random = new SecureRandom();
            byte[] randBytes = new byte[20];
            random.nextBytes(randBytes);
            String salt = new String(randBytes);

            PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), salt.getBytes("UTF-8"), pswdIterations, keySize);
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            return factory.generateSecret(spec);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException | UnsupportedEncodingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
