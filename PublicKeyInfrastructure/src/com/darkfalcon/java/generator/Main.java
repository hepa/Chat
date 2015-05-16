/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.darkfalcon.java.generator;

import com.darkfalcon.java.config.ApplicationConfig;
import com.darkfalcon.java.connection.ReceiveConnectionWorker;
import com.darkfalcon.java.entity.server.KeyRegistry;
import com.darkfalcon.java.file.util.SecurityFileExporter;
import com.darkfalcon.java.file.util.SecurityFileImporter;
import com.darkfalcon.java.gson.ByteArrayToBase64TypeAdapter;
import com.darkfalcon.java.message.Message;
import com.darkfalcon.java.message.Opcode;
import com.darkfalcon.java.services.SecurityService;
import com.darkfalcon.java.services.ServiceProvider;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.codec.binary.Base64;
import com.darkfalcon.java.keys.AsymmetricKeyUtil;
import com.darkfalcon.java.security.SecurityProvider;
import com.darkfalcon.java.security.SecurityProviderImpl;
import com.darkfalcon.java.signature.Signer;
import com.darkfalcon.java.utils.FileOperationUtil;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.SignatureException;

/**
 *
 * @author Darkfalcon
 */
public class Main {

    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeySpecException, IOException, SignatureException, NoSuchProviderException, InvalidKeyException {
        
        
        
        
        /*String workingDir = System.getProperty("user.dir");
        String pathPriv = workingDir + "\\files\\private.key";
        String pathPub = workingDir + "\\files\\public.key";
        String pathSig = workingDir + "\\files\\public.sig";
        
        KeyPair keypair = AsymmetricKeyUtil.generateKeyPair(AsymmetricKeyUtil.ALGORITHM_RSA);
        
        System.out.println(SecurityFileExporter.exportPrivateKey(pathPriv, keypair.getPrivate()));
        System.out.println(SecurityFileExporter.exportPublicKey(pathPub, keypair.getPublic()));
        
        System.out.println(keypair.getPublic());
        System.out.println(Arrays.toString(keypair.getPrivate().getEncoded()));
        System.out.println("-----------------------------------------------");
        
        PrivateKey privateKey = SecurityFileImporter.importPrivateKey(pathPriv, AsymmetricKeyUtil.ALGORITHM_RSA);
        PublicKey publicKey = SecurityFileImporter.importPublicKey(pathPub, AsymmetricKeyUtil.ALGORITHM_RSA);
        
        System.out.println(publicKey);
        System.out.println(Arrays.toString(privateKey.getEncoded()));
        
        KeyPair keypairDSA = AsymmetricKeyUtil.generateKeyPair(AsymmetricKeyUtil.ALGORITHM_DSA, AsymmetricKeyUtil.PROVIDER_SUN);
        System.out.println(keypairDSA.getPublic());
        System.out.println(Arrays.toString(keypairDSA.getPrivate().getEncoded()));
        
        SecurityProvider provider = new SecurityProviderImpl();
        Signer signer = provider.getSigner(AsymmetricKeyUtil.KEY_SIZE_2048);
        byte[] signature = signer.sign(AsymmetricKeyUtil.encodePublicKey(publicKey));
        
        System.out.println("signature: " + Arrays.toString(signature));
        
        System.out.println(FileOperationUtil.writeToFile(pathSig, signature));
        
        byte[] signatureRead = FileOperationUtil.readFromFile(pathSig);
        
        signer.verify(signatureRead, AsymmetricKeyUtil.encodePublicKey(publicKey));*/
        
        
        
        

        /*System.out.println(ApplicationConfig.getConfig().getProperty("listen-port"));
        KeyPair keyPair = AsymmetricKeyUtil.generateKeyPair(AsymmetricKeyUtil.ALGORITHM_RSA);
        Message<String> message = new Message<String>();
        message.setOpcode(Opcode.CONNECT);
        String content = new Base64().encodeAsString(keyPair.getPublic().getEncoded());
        message.setContent(content);
        System.out.println(keyPair.getPublic());

        Gson gson = new Gson();
        String em = gson.toJson(message);
        System.out.println(em);
        Message<String> m = gson.fromJson(em, new TypeToken<Message<String>>() {
        }.getType());
        byte[] dec = new Base64().decode(m.getContent());
        PublicKey publicKey
                = AsymmetricKeyUtil.decodePublicKey(dec, AsymmetricKeyUtil.ALGORITHM_RSA);
        System.out.println(publicKey);*/

        /*Server server = new Server();
         server.setServerId("seerver1");
         KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
         keyGen.initialize(1024);
         KeyPair keyPair = keyGen.generateKeyPair();
         server.setKeyPair(keyPair);
         service.createServerRegistry(server);
        
         server = new Server();
         server.setServerId("seerver2");
         keyGen = KeyPairGenerator.getInstance("RSA");
         keyGen.initialize(1024);
         keyPair = keyGen.generateKeyPair();
         server.setKeyPair(keyPair);
         service.createServerRegistry(server);*/
        /*server = service.getServerRegistryByServerId("chat_server");
         System.out.println(server.getServerId());
         System.out.println(server.getKeyPair().getPrivate());
         System.out.println(server.getKeyPair().getPublic());*/
        
        

    }
}
