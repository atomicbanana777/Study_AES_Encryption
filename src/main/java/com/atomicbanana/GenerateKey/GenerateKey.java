package com.atomicbanana.GenerateKey;

import javax.crypto.KeyGenerator;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class GenerateKey {
    
    public static final String cipher = "AES";
    public static final int keySize = 128;

    public static String generateKeyString() throws NoSuchAlgorithmException, IOException{
        KeyGenerator keyGenerator = KeyGenerator.getInstance(cipher);
        keyGenerator.init(keySize);
        byte[] key = keyGenerator.generateKey().getEncoded();
        return Base64.getEncoder().encodeToString(key);
    }
}
