package com.atomicbanana.encrypt;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

import com.atomicbanana.properties.MyProperties;

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

    public static String AESEncrypt_PB(String strToEncrypt, String PBKey) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException, InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException{
        MyProperties myProp = new MyProperties("MyProp.prop");
        SecureRandom secureRandom = new SecureRandom();
        byte[] iv = new byte[16];
        secureRandom.nextBytes(iv);
        IvParameterSpec ivspec = new IvParameterSpec(iv);

        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        KeySpec spec = new PBEKeySpec(PBKey.toCharArray(), myProp.getSalt().getBytes(), Integer.parseInt(myProp.getIterationCount()), Integer.parseInt(myProp.getKeyLength()));
        SecretKey tmp = factory.generateSecret(spec);
        SecretKeySpec secretKeySpec = new SecretKeySpec(tmp.getEncoded(), "AES");

        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivspec);

        byte[] cipherText = cipher.doFinal(strToEncrypt.getBytes("UTF-8"));
        byte[] encryptedData = new byte[iv.length + cipherText.length];
        System.arraycopy(iv, 0, encryptedData, 0, iv.length);
        System.arraycopy(cipherText, 0, encryptedData, iv.length, cipherText.length);

        return Base64.getEncoder().encodeToString(encryptedData);
    }

    public static String AESDecrypt_PB(String strToDecrypt, String PBKey) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException, InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException{
        MyProperties myProp = new MyProperties("MyProp.prop");
        byte[] encryptedData = Base64.getDecoder().decode(strToDecrypt);
        byte[] iv = new byte[16];
        System.arraycopy(encryptedData, 0, iv, 0, iv.length);
        IvParameterSpec ivspec = new IvParameterSpec(iv);

        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        KeySpec spec = new PBEKeySpec(PBKey.toCharArray(), myProp.getSalt().getBytes(), Integer.parseInt(myProp.getIterationCount()), Integer.parseInt(myProp.getKeyLength()));
        SecretKey tmp = factory.generateSecret(spec);
        SecretKeySpec secretKeySpec = new SecretKeySpec(tmp.getEncoded(), "AES");

        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivspec);

        byte[] cipherText = new byte[encryptedData.length - 16];
        System.arraycopy(encryptedData, 16, cipherText, 0, cipherText.length);

        byte[] decryptedText = cipher.doFinal(cipherText);
        return new String(decryptedText, "UTF-8");
    }
}
