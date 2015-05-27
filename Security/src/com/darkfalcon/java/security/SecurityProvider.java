package com.darkfalcon.java.security;

import com.darkfalcon.java.encryption.Encrypter;

public interface SecurityProvider {

    public Encrypter getAsymmetricEncryption();

    public Encrypter getSymmetricEncryption();
}
