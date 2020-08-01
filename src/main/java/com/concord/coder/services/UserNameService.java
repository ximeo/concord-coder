package com.concord.coder.services;

import com.concord.coder.model.User;

public interface UserNameService {

  String getEncryptedFullName(int id);
  String getFullName(String encodedName);
}
