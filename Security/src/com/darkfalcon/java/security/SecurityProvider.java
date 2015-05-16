package com.darkfalcon.java.security;

import com.darkfalcon.java.encryption.Encrypter;
import com.darkfalcon.java.signature.Signer;

public interface SecurityProvider {

    public Encrypter getAsymmetricEncryption(int keySize);

    public Encrypter getSymmetricEncryption();
}
