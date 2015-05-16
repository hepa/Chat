/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.darkfalcon.java.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


/**
 *
 * @author Darkfalcon
 */
public class FileOperationUtil {
    
    public static boolean writeToFile(String path, byte[] content) throws FileNotFoundException, IOException {
        File file = new File(path);
        File parent = file.getParentFile();

        if (!parent.exists() && !parent.mkdirs()) {
            throw new IllegalStateException("Couldn't create dir: " + parent);
        }

        try (OutputStream outputStream = new FileOutputStream(file)) {
            outputStream.write(content);
        }

        return true;
    }
    
    public static byte[] readFromFile(String path) throws FileNotFoundException, IOException {
        final File file = new File(path);
        byte[] buffer = new byte[(int) file.length()];
        
        try (InputStream inputStream = new FileInputStream(file)) {
            inputStream.read(buffer);
        }
        
        return buffer;
    }
}
