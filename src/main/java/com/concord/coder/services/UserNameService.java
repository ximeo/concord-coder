package com.concord.coder.services;


public interface UserNameService {

  String getEncryptedFullName(int id);
  String getFullName(String encodedName);
}
