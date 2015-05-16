package security;

import com.darkfalcon.java.encryption.Encrypter;
import java.io.UnsupportedEncodingException;
import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidParameterSpecException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AESEncrypter
        implements Encrypter {

    private byte[] ivBytes;
    private SecretKeySpec secretSpec;
    private Cipher cipher;

    public AESEncrypter(SecretKey key) {
        this.secretSpec = new SecretKeySpec(key.getEncoded(), "AES");
        try {
            this.cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public byte[] encrypt(String plain) {
        try {
            this.cipher.init(1, this.secretSpec);
            AlgorithmParameters params = this.cipher.getParameters();
            this.ivBytes = ((IvParameterSpec) params.getParameterSpec(IvParameterSpec.class)).getIV();
            return this.cipher.doFinal(plain.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException | IllegalBlockSizeException | BadPaddingException | InvalidKeyException | InvalidParameterSpecException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public String decrypt(byte[] secret) {
        try {
            this.cipher.init(2, this.secretSpec, new IvParameterSpec(
                    this.ivBytes));
            return new String(this.cipher.doFinal(secret));
        } catch (InvalidKeyException | InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
