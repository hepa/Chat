package com.darkfalcon.java.security;

import com.darkfalcon.java.encryption.Encrypter;
import com.darkfalcon.java.encryption.impl.RSAEncrypter;
import com.darkfalcon.java.keys.AESKeyUtil;
import com.darkfalcon.java.signature.Signer;
import com.darkfalcon.java.signature.impl.DSASigner;
import security.AESEncrypter;
import com.darkfalcon.java.keys.AsymmetricKeyUtil;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SecurityProviderImpl implements SecurityProvider {

    @Override
    public Encrypter getAsymmetricEncryption() {
        return new RSAEncrypter(AsymmetricKeyUtil.generateKeyPair(
                AsymmetricKeyUtil.ALGORITHM_RSA, null));
    }

    @Override
    public Encrypter getSymmetricEncryption() {
        try {
            return new AESEncrypter(AESKeyUtil.generateKey(128));
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(SecurityProviderImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex);
        }
    }

    /*@Override
    public Signer getSigner(int keySize) {
        return new DSASigner(AsymmetricKeyUtil.generateKeyPair(
                keySize, AsymmetricKeyUtil.ALGORITHM_DSA, AsymmetricKeyUtil.PROVIDER_SUN));
    }*/
}
