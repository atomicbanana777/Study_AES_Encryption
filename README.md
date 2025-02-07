# Study_AES_Encryption
Study AES Encryption

The is a Maven Java project.

It aims to study on how to encrypt and decrypt password.

## Why AES

I choose to use AES as the encryption algorithm since I think it is the most popular one.

AES is a __symmetrical encryption algorithm__. 

The feature of symmetrical is that you have a key that is used for both encrypt and decrypt.

In contrast to asymmetrical such as RSA, it requires 2 keys which are public key and private key. 

Public key use for encrypt and private key used for decrypt.

While asymmetrical is more secure, symmetrical is more easy to use.

So let's study AES first.

## About AES Key

AES comes with 3 standards, AES_128, AES_192, AES_256

AES_128 means the key we are using is 128 bits. 

Which means the key is restricted to have 16 characters (128 bits = 16 bytes = 16 characters)

AES_192 = 24 characters key

AES_256 = 32 characters key


Key is just a string. It can be input by human or generated using program.

In this project, the key is located in `src/resources/MyProp.prop`

The key is generated using program `GenerateKey.java`

You can input your own key but it has to be 128 bits, 192 bits or 256 bits.


## To package

  you may run `chmod 755 mvnw` in order to execute Maven wrapper `./mvnw`
  run `./mvnw package`

## To execute
  run `java -jar target/studyencrypt-1.0-SNAPSHOT.jar`

## Option
  - For generate a AES key, you can run `java -jar target/studyencrypt-1.0-SNAPSHOT.jar genKey`
    
    It generates a AES key and output in the console.
    
    Noted that it will not update any files or properties.
    
    If you want to use the key for encrypt and decrypt, you have to update it in MyProp.prop.
    
  
  - For encrypt, run `java -jar target/studyencrypt-1.0-SNAPSHOT.jar encrypt <The password you want to encrypt>`

    It encrypt the password you provide using the key in MyProp.prop.

    And print the encrypted password out in console.

    Noted that the output you saw in console was encoded by Base64.

    Since the actual encrypted password is a byte[], its not readable by human.
  
  - For decrypt, run `java -jar target/studyencrypt-1.0-SNAPSHOT.jar decrypt <The encrypted password>`

    It decrypt the encrypted password and print out in the console

## About this Study
  I know more about encryption such as Symmetric and Asymmetric.
  I originally planned to add `Salt` and `Hashing Algorithm` to make the key more complex but later I think the generate key is enough.
  If the key is human input, we may need to add them.

  May be I should study Asymmetric next time.

  I have also made some unit tests. I think I need to learn more about unit test.
