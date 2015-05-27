package com.darkfalcon.java.keys;

import java.security.NoSuchAlgorithmException;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class AESKeyUtil {
    
    public static final int SIZE_128 = 128;
    public static final int SIZE_192 = 192;
    public static final int SIZE_256 = 256;

    public static SecretKey generateKey(int size) throws NoSuchAlgorithmException {
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        kgen.init(size);
        return kgen.generateKey();
    }

    public static SecretKey generateSecretKeyFromBytes(byte[] encodedSecret) {
        return new SecretKeySpec(encodedSecret, 0, encodedSecret.length, "AES");
    }
}
