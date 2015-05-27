/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.darkfalcon.java.file.util;

import com.darkfalcon.java.keys.AsymmetricKeyUtil;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;

/**
 *
 * @author Darkfalcon
 */
public class SecurityFileImporter {

    public static PublicKey importPublicKey(String path, String algorithm) throws NoSuchAlgorithmException, InvalidKeySpecException, IOException {
        return AsymmetricKeyUtil.decodePublicKey(FileOperationUtil.readFromFile(path), algorithm);
    }

    public static PrivateKey importPrivateKey(String path, String algorithm) throws NoSuchAlgorithmException, IOException, InvalidKeySpecException {
        return AsymmetricKeyUtil.decodePrivateKey(FileOperationUtil.readFromFile(path), algorithm);
    }

    
}
