package com.concord.coder.util;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;


public class CryptoHelper {
  private static final String ENCRYPT_ALGORITHM = "AES/GCM/NoPadding";
  private static final String KEY_FACTORY_ALGORITHM = "PBKDF2WithHmacSHA256";
  private static final String SECRET_KEY_ALGORITHM = "AES";
  private static final int TAG_LENGTH_BIT = 128; // must be one of {128, 120, 112, 104, 96}
  private static final int IV_LENGTH_BYTE = 12;
  private static final int SALT_LENGTH_BYTE = 16;
  private static final Charset UTF_8 = StandardCharsets.UTF_8;

  public static String encrypt(String pText, String password) throws Exception {
    return encrypt(pText.getBytes(UTF_8), password);
  }

  public static String encrypt(byte[] pText, String password) throws Exception {
    byte[] salt = getRandomNonce(SALT_LENGTH_BYTE);
    byte[] iv = getRandomNonce(IV_LENGTH_BYTE);
    SecretKey aesKeyFromPassword = getAESKeyFromPassword(password.toCharArray(), salt);
    Cipher cipher = Cipher.getInstance(ENCRYPT_ALGORITHM);
    cipher.init(Cipher.ENCRYPT_MODE, aesKeyFromPassword, new GCMParameterSpec(TAG_LENGTH_BIT, iv));
    byte[] cipherText = cipher.doFinal(pText);
    byte[] cipherTextWithIvSalt = ByteBuffer.allocate(iv.length + salt.length + cipherText.length)
            .put(iv)
            .put(salt)
            .put(cipherText)
            .array();
    return Base64.getEncoder().encodeToString(cipherTextWithIvSalt);
  }

  public static String decrypt(String cText, String password) throws Exception {
    byte[] decode = Base64.getDecoder().decode(cText.getBytes(UTF_8));
    ByteBuffer bb = ByteBuffer.wrap(decode);
    byte[] iv = new byte[IV_LENGTH_BYTE];
    bb.get(iv);
    byte[] salt = new byte[SALT_LENGTH_BYTE];
    bb.get(salt);
    byte[] cipherText = new byte[bb.remaining()];
    bb.get(cipherText);
    SecretKey aesKeyFromPassword = getAESKeyFromPassword(password.toCharArray(), salt);
    Cipher cipher = Cipher.getInstance(ENCRYPT_ALGORITHM);
    cipher.init(Cipher.DECRYPT_MODE, aesKeyFromPassword, new GCMParameterSpec(TAG_LENGTH_BIT, iv));
    byte[] plainText = cipher.doFinal(cipherText);
    return new String(plainText, UTF_8);
  }

  private static byte[] getRandomNonce(int numBytes) {
    byte[] nonce = new byte[numBytes];
    new SecureRandom().nextBytes(nonce);
    return nonce;
  }

  private static SecretKey getAESKey(int keySize) throws NoSuchAlgorithmException {
    KeyGenerator keyGen = KeyGenerator.getInstance(SECRET_KEY_ALGORITHM);
    keyGen.init(keySize, SecureRandom.getInstanceStrong());
    return keyGen.generateKey();
  }

  private static SecretKey getAESKeyFromPassword(char[] password, byte[] salt) throws NoSuchAlgorithmException, InvalidKeySpecException {
    SecretKeyFactory factory = SecretKeyFactory.getInstance(KEY_FACTORY_ALGORITHM);
    KeySpec spec = new PBEKeySpec(password, salt, 65536, 256);
    SecretKey secret = new SecretKeySpec(factory.generateSecret(spec).getEncoded(), SECRET_KEY_ALGORITHM);
    return secret;
  }
}
