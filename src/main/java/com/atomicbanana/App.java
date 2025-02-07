package com.atomicbanana;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import com.atomicbanana.GenerateKey.GenerateKey;
import com.atomicbanana.encrypt.Encryptor;
import com.atomicbanana.properties.MyProperties;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException
    {
        System.out.println( "==============================================" );
        System.out.println( "Hi, you can use below keyword as args: " );
        System.out.println( "genKey" );
        System.out.println( "genKey <key size>" );
        System.out.println( "encrypt <password>" );
        System.out.println( "encrypt <password> <key>" );
        System.out.println( "decrypt <encrypted password>" );
        System.out.println( "decrypt <encrypted password> <key>" );
        System.out.println( "==============================================" );
        if(args.length == 1 && args[0].equals("genKey")){
            System.out.println(GenerateKey.generateKeyString());
        }

        if(args.length == 2 && args[0].equals("genKey")){
            System.out.println(GenerateKey.generateKeyString(Integer.parseInt(args[1])));
        }

        if(args.length == 2 && args[0].equals("encrypt")){
            MyProperties prop = new MyProperties("MyProp.prop");
            System.out.println(Encryptor.AESEncrypt(args[1], prop.getKey()));
        }

        if(args.length == 2 && args[0].equals("decrypt")){
            MyProperties prop = new MyProperties("MyProp.prop");
            System.out.println(Encryptor.AESDecrypt(args[1], prop.getKey()));
        }

        if(args.length == 3 && args[0].equals("encrypt")){
            System.out.println(Encryptor.AESEncrypt(args[1], args[2]));
        }

        if(args.length == 3 && args[0].equals("decrypt")){
            System.out.println(Encryptor.AESDecrypt(args[1], args[2]));
        }
    }
}


