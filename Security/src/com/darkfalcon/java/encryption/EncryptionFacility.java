package com.darkfalcon.java.encryption;

import com.darkfalcon.java.security.SecurityProvider;
import com.darkfalcon.java.signature.Signer;

public class EncryptionFacility {

    private static Encrypter asymmetric;
    private static Encrypter symmetric;
    private static Signer signer;

    public static void init(SecurityProvider provider) {
        /*asymmetric = provider.getAsymmetricEncryption();
        symmetric = provider.getSymmetricEncryption();
        signer = provider.getSigner();*/
    }

    public static Encrypter getAsymmetric() {
        return asymmetric;
    }

    public static Encrypter getSymmetric() {
        return symmetric;
    }
    
    public static Signer getSigner() {
        return signer;
    }
}
