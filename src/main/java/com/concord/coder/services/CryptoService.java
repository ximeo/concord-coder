package com.concord.coder.services;

//import org.springframework.security.crypto.encrypt.Encryptors;
//import org.springframework.security.crypto.encrypt.TextEncryptor;
//import org.springframework.security.crypto.keygen.KeyGenerators;
import org.springframework.stereotype.Service;

//@Service
//public class CryptoService {
//  private static final String CODER_WORD = "AES";
//
//  private TextEncryptor encryptor;
//
////  @Autowired
//  public CryptoService() {
//    String salt = KeyGenerators.string().generateKey();
//    this.encryptor = Encryptors.text(CODER_WORD, salt);
//  }
//
//  public String encrypt(String textToCrypt) {
//    return encryptor.encrypt(textToCrypt);
//  }
//
//  public String decrypt(String textToDecrypt) {
//    return encryptor.decrypt(textToDecrypt);
//  }
//}
