package com.atomicbanana;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.junit.Test;

import com.atomicbanana.GenerateKey.GenerateKey;
import com.atomicbanana.encrypt.Encryptor;
import com.atomicbanana.properties.MyProperties;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    @Test
    public void IsKeyGenerateCorrect() throws IOException, NoSuchAlgorithmException{
        //AES128 accept only 128 bits as key
        //First, we get the length of bytes
        int lenghtInBytes = Base64.getDecoder().decode(GenerateKey.generateKeyString()).length;

        //Then bytes * 8 = bits
        assertEquals(128, lenghtInBytes * 8);
    }

    @Test
    public void IsKeyCorrect() throws IOException{
        MyProperties myprop = new MyProperties("MyProp.prop");
        assertEquals("NzEq29NJLBKap07QYnd/Rw==", myprop.getKey());
    }

    @Test
    public void EncryptCorrectly() throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException{
        String input = "password1234";
        String encrypted = Encryptor.AESEncrypt(input, "NzEq29NJLBKap07QYnd/Rw==");
        String decrypted = Encryptor.AESDecrypt(encrypted, "NzEq29NJLBKap07QYnd/Rw==");
        assertEquals(input, decrypted);
    }
}
