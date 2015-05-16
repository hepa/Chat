/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.darkfalcon.java.utils;

import com.darkfalcon.java.entity.server.KeyRegistry;
import com.darkfalcon.java.file.util.SecurityFileExporter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.darkfalcon.java.keys.AsymmetricKeyUtil;
import java.security.InvalidKeyException;
import java.security.NoSuchProviderException;
import java.security.SignatureException;

/**
 *
 * @author Darkfalcon
 */
public class KeyExportUtil {

    public static void exportRSAKeys(KeyRegistry keyRegistry) throws FileNotFoundException, IOException {
        String workingDir = System.getProperty("user.dir");
        String pathPriv = workingDir + "\\" + keyRegistry.getServerId() + "\\private.key";
        String pathPub = workingDir + "\\" + keyRegistry.getServerId() + "\\public.key";
        String pathSig = workingDir + "\\" + keyRegistry.getServerId() + "\\public.sig";
        
        SecurityFileExporter.exportPrivateKey(pathPriv, keyRegistry.getKeyPair().getPrivate());
        SecurityFileExporter.exportPublicKey(pathPub, keyRegistry.getKeyPair().getPublic());

        FileOperationUtil.writeToFile(pathSig, generateSignature(keyRegistry));
    }

    private static byte[] generateSignature(KeyRegistry keyRegistry) throws IOException {
        try {
            return SignProvider.getInstance().getSigner().sign(AsymmetricKeyUtil.encodePublicKey(keyRegistry.getKeyPair().getPublic()));
        } catch (SignatureException | NoSuchProviderException | NoSuchAlgorithmException | InvalidKeyException ex) {
            Logger.getLogger(KeyExportUtil.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex);
        }
    }
}
