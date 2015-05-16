package com.darkfalcon.java.encryption;

public interface Encrypter {

    public byte[] encrypt(String paramString);

    public String decrypt(byte[] paramArrayOfByte);
}
