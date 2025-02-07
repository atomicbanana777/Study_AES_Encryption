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
    public void Is192KeyGenerateCorrect() throws IOException, NoSuchAlgorithmException{
        //generate 192 bits as key
        int lenghtInBytes = Base64.getDecoder().decode(GenerateKey.generateKeyString(192)).length;

        //Then bytes * 8 = bits
        assertEquals(192, lenghtInBytes * 8);
    }

    @Test
    public void Is256KeyGenerateCorrect() throws IOException, NoSuchAlgorithmException{
        //generate 256 bits as key
        int lenghtInBytes = Base64.getDecoder().decode(GenerateKey.generateKeyString(256)).length;

        //Then bytes * 8 = bits
        assertEquals(256, lenghtInBytes * 8);
    }

    @Test
    public void EncryptCorrectly() throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException{
        String input = "reghreoghhhtuitrgu45867548697548-678459egihregire^&$%@#*($&@#*(&$/ewfewifhj))32423";
        String encrypted = Encryptor.AESEncrypt(input, "NzEq29NJLBKap07QYnd/Rw==");
        assertEquals("3GZ04ekQjm9X17uC/NPox+Es+svEJwm8O4saPhY27IwJMTdBCKPhRkHujsX1Sz36aFeSPDI0Kds222WsnBEMx2BXzzrEO6ST6Z0tHSn9gGOSUhzwent3cEJf4msWQiVn", encrypted);
    }

    @Test
    public void DecryptCorrectly() throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException{
        String input = "password1234";
        String encrypted = Encryptor.AESEncrypt(input, "NzEq29NJLBKap07QYnd/Rw==");
        String decrypted = Encryptor.AESDecrypt(encrypted, "NzEq29NJLBKap07QYnd/Rw==");
        assertEquals(input, decrypted);
    }

    @Test
    public void Encrypt256Correctly() throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException{
        String input = "reghreoghhhtuitrgu45867548697548-678459egihregire^&$%@#*($&@#*(&$/ewfewifhj))32423";
        String encrypted = Encryptor.AESEncrypt(input, "T67ZFB8ztveO+5weCn4Cdt0WjxqwD7WR4/0oqQV3gQY=");
        assertEquals("nPdCX2AsVI71zrG1p/SYoenqXyBVq+PGh8cMOkDX71dk+GsN+6+efQl2jQS1F0uz8Qe8r49a2/9/aLs6ijxr1eYalOqigxZdGaFb5Wl4yQECGqV57a8ZCl/B2snN0rnc", encrypted);
    }

    @Test
    public void Decrypt256Correctly() throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException{
        String input = "password1234";
        String encrypted = Encryptor.AESEncrypt(input, "T67ZFB8ztveO+5weCn4Cdt0WjxqwD7WR4/0oqQV3gQY=");
        String decrypted = Encryptor.AESDecrypt(encrypted, "T67ZFB8ztveO+5weCn4Cdt0WjxqwD7WR4/0oqQV3gQY=");
        assertEquals(input, decrypted);
    }

    @Test
    public void Encrypt192Correctly() throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException{
        String input = "regre5460puir9wg";
        String encrypted = Encryptor.AESEncrypt(input, "FU7dRNK27wX+i780GaK36cq16e0BIBOL");
        assertEquals("gTLpDr0mbwCxDcfy+8d7G1P6/3HMVLCTZihWmiw4S2E=", encrypted);
    }

    @Test
    public void Decrypt192Correctly() throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException{
        String input = "regre5460puir9wg";
        String encrypted = Encryptor.AESEncrypt(input, "FU7dRNK27wX+i780GaK36cq16e0BIBOL");
        String decrypted = Encryptor.AESDecrypt(encrypted, "FU7dRNK27wX+i780GaK36cq16e0BIBOL");
        assertEquals(input, decrypted);
    }
}
