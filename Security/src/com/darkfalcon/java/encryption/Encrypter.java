package com.darkfalcon.java.encryption;

public interface Encrypter {

    public byte[] encrypt(String plain);
    
    public byte[] encryptToByte(byte[] plainBytes);

    public String decrypt(byte[] secretBytes);
    
    public byte[] decryptToByte(byte[] secretBytes);
}
