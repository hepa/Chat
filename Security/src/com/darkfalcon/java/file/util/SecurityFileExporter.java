/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.darkfalcon.java.file.util;

import com.darkfalcon.java.keys.AsymmetricKeyUtil;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.PrivateKey;
import java.security.PublicKey;

/**
 *
 * @author Darkfalcon
 */
public class SecurityFileExporter {

    public static boolean exportPublicKey(String path, PublicKey publicKey) throws FileNotFoundException, IOException {
        return FileOperationUtil.writeToFile(path, AsymmetricKeyUtil.encodePublicKey(publicKey));
    }

    public static boolean exportPrivateKey(String path, PrivateKey privateKey) throws FileNotFoundException, IOException {
        return FileOperationUtil.writeToFile(path, AsymmetricKeyUtil.encodePrivateKey(privateKey));
    }
}
