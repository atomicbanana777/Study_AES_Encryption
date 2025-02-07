package com.atomicbanana.encrypt;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

public class Encryptor {
    
    private static String ALGORITHM = "AES";
    
    public static String AESEncrypt(String input, String keyString) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException{
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        Key key = new SecretKeySpec(Base64.getDecoder().decode(keyString), ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, key);

        byte[] encValue = cipher.doFinal(input.getBytes());
        String reval = Base64.getEncoder().encodeToString(encValue);
        return reval;
    }

    public static String AESDecrypt(String input, String keyString) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException{
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        Key key = new SecretKeySpec(Base64.getDecoder().decode(keyString), ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, key);
        
        byte[] inputValueByte = Base64.getDecoder().decode(input.getBytes());
        byte[] encValue = cipher.doFinal(inputValueByte);

        String reval = new String(encValue);
        return reval;
    }
}
