package com.concord.coder.services.impl;

import com.concord.coder.dao.UserRepository;
import com.concord.coder.exceptions.UserNameCantBeObtainedException;
import com.concord.coder.model.User;
import com.concord.coder.services.UserNameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

import static com.concord.coder.util.CryptoHelper.*;

@Service
@Transactional
public class UserNameServiceImpl implements UserNameService {

  private final UserRepository userRepository;
  private final String password;

  @Autowired
  public UserNameServiceImpl(UserRepository userRepository, @Value("${crypto.password}") String cryptoPassword) {
    this.userRepository = userRepository;
    this.password = cryptoPassword;
  }

  @Override
  @Transactional(readOnly = true)
  public String getEncryptedFullName(int id) {
    return userRepository.findById(id)
            .map(User::getFullName)
            .map(n -> {
              try {
                return encrypt(n, password);
              } catch (Exception ex) {
                throw new UserNameCantBeObtainedException("User full name can`t be encrypted correctly", ex);
              }
            }).orElseThrow(() -> new UserNameCantBeObtainedException("User with this id can`t be found"));
  }

  @Override
  public String getFullName(String encryptedName) {
    try {
      return decrypt(encryptedName, password);
    } catch (Exception ex) {
      throw new UserNameCantBeObtainedException("User full name can`t be decrypted correctly", ex);
    }
  }
}
