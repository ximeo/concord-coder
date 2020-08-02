package com.concord.coder.services;

import com.concord.coder.dao.UserRepository;
import com.concord.coder.model.User;
import com.concord.coder.services.impl.UserNameCryptoService;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static com.concord.coder.util.CryptoHelper.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class UserNameServiceTest {
  private static final int EXISTENT_USER_ID = 1;
  private static final int NON_EXISTENT_USER_ID = 2;
  private static final String ENCRYPTED_FULL_USER_NAME_UNCORRECT = "DZ1Yr5t5EPlVabNkEp08uWvxpzyNg1bUNJeMVZi8Vf4IaoqaD1+r1qN5YoMDh6rhPvA5XokRtw==";
  private static final String USER_FULL_NAME = "Test Testov";
  private static final String PASSWORD = "password";
  private static final String USER_CANT_BE_FOUND_ERROR_MESSAGE = "User with this id can`t be found";
  private static final String USER_CANT_BE_DECRYPTED_ERROR_MESSAGE = "User full name can`t be decrypted correctly";

  private static String ENCRYPTED_FULL_USER_NAME_CORRECT;
  private UserNameService testedInstance;
  private UserRepository userRepository;

  @Before
  public void init() throws Exception {
    userRepository = mock(UserRepository.class);
    User user = new User();
    user.setId(EXISTENT_USER_ID);
    user.setFullName(USER_FULL_NAME);
    when(userRepository.findById(EXISTENT_USER_ID)).thenReturn(Optional.of(user));
    testedInstance = new UserNameCryptoService(userRepository, PASSWORD);
    ENCRYPTED_FULL_USER_NAME_CORRECT = encrypt(USER_FULL_NAME, PASSWORD);

  }

  @Test
  public void shouldReturnCorrectEncodedFullName() throws Exception {

    String response = testedInstance.getEncryptedFullName(EXISTENT_USER_ID);

    assertEquals(USER_FULL_NAME, decrypt(response, PASSWORD));
    verify(userRepository, times(1)).findById(EXISTENT_USER_ID);
    verify(userRepository, times(1)).findById(EXISTENT_USER_ID);
  }

  @Test
  public void shouldThrowCorrectExceptionOnNonExistedUserId() {
    try {
      String response = testedInstance.getEncryptedFullName(NON_EXISTENT_USER_ID);
    } catch (Exception ex) {
      assertEquals(ex.getMessage(), USER_CANT_BE_FOUND_ERROR_MESSAGE);
      assertNull(ex.getCause());
    }
  }

  @Test
  public void shouldReturnCorrectFullNameOnEncodedName() {

    String response = testedInstance.getFullName(ENCRYPTED_FULL_USER_NAME_CORRECT);

    assertEquals(USER_FULL_NAME, response);
  }

  @Test
  public void shouldThrowCorrectExceptionOnUnCorrectEncodedName() {
    try {
      String response = testedInstance.getFullName(ENCRYPTED_FULL_USER_NAME_UNCORRECT);
    } catch (Exception ex) {
      assertEquals(ex.getMessage(), USER_CANT_BE_DECRYPTED_ERROR_MESSAGE);
      assertNotNull(ex.getCause());
    }
  }
}
