package com.concord.coder.util;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;

//import org.springframework.security.crypto.encrypt.Encryptors;
//import org.springframework.security.crypto.encrypt.TextEncryptor;
//import org.springframework.security.crypto.keygen.KeyGenerators;

public class UserFullNameEncryptor {
//
//  public static byte[] encrypt(byte[] src, String password) {
//    Aes256Coder aes256 = new Aes256Coder(password);
//
//    byte[] shifr = aes256.makeAes(src, Cipher.ENCRYPT_MODE);
//    return shifr;
//  }
//
//  public static byte[] decrypt(byte[] shifr, String password) {
//    Aes256Coder aes256 = new Aes256Coder(password);
//
//    byte[] src = aes256.makeAes(shifr, Cipher.DECRYPT_MODE);
//    return src;
//  }
//
//  public String encrypt(String text, String password) {
//    String salt = KeyGenerators.string().generateKey();
//    TextEncryptor encryptor = Encryptors.text(password, salt);
//    String cipherText = encryptor.encrypt(text);
//  }
//
//  private static class Aes256Coder {
//    private SecretKey secretKey;
//    private String password;
//
//    public Aes256Coder(String password) {
//      try {
//        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
//        keyGenerator.init(256);
//        this.secretKey = keyGenerator.generateKey();
////        this.password = password;
//      } catch (NoSuchAlgorithmException e) {
//        e.printStackTrace();
//      }
//    }
//
//    public byte[] makeAes(byte[] rawMessage, int cipherMode) {
//      try {
//        Cipher cipher = Cipher.getInstance("AES");
//        cipher.init(cipherMode, this.secretKey);
//        byte[] output = cipher.doFinal(rawMessage);
//        return output;
//      } catch (Exception e) {
//        e.printStackTrace();
//        return null;
//      }
//    }
//  }
//
//  public static void main(String[] args) {
//    String name = "Test";
//    String password = "AES";
//    byte[] encryptedName = encrypt(name.getBytes(), password);
//    System.out.println("Encrypted name: " + new String(encryptedName));
//    byte[] decryptedName = decrypt(encryptedName, password);
//    System.out.println("Decrypted name: " + new String(decryptedName));
//  }
//

}
