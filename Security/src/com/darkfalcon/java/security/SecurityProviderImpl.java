package com.darkfalcon.java.security;

import com.darkfalcon.java.encryption.Encrypter;
import com.darkfalcon.java.encryption.impl.RSAEncrypter;
import com.darkfalcon.java.keys.AESKeyUtil;
import com.darkfalcon.java.signature.Signer;
import com.darkfalcon.java.signature.impl.DSASigner;
import security.AESEncrypter;
import com.darkfalcon.java.keys.AsymmetricKeyUtil;

public class SecurityProviderImpl implements SecurityProvider {

    @Override
    public Encrypter getAsymmetricEncryption(int keySize) {
        return new RSAEncrypter(AsymmetricKeyUtil.generateKeyPair(
                keySize, AsymmetricKeyUtil.ALGORITHM_RSA, null));
    }

    @Override
    public Encrypter getSymmetricEncryption() {
        return new AESEncrypter(AESKeyUtil.generateKey());
    }

    /*@Override
    public Signer getSigner(int keySize) {
        return new DSASigner(AsymmetricKeyUtil.generateKeyPair(
                keySize, AsymmetricKeyUtil.ALGORITHM_DSA, AsymmetricKeyUtil.PROVIDER_SUN));
    }*/
}
