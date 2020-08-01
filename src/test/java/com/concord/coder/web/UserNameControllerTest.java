package com.concord.coder.web;

import com.concord.coder.dto.UserNameRequestDTO;
import com.concord.coder.dto.UserNameResponseDTO;
import com.concord.coder.exceptions.UserNameCantBeObtainedException;
import com.concord.coder.services.UserNameService;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

//@RunWith(SpringRunner.class)
//@WebMvcTest(UserNameController.class)
public class UserNameControllerTest {
  private static final String ENCRYPTED_FULL_USER_NAME_CORRECT = "DZ1Yr5t5EPlVkmNkEp08uWvxpzyNg1bUNJeMVZi8Vf4IaoqaD1+r1qN5YoMDh6rhPvA5XokRtw==";
  private static final String ENCRYPTED_FULL_USER_NAME_UNCORRECT = "DZ1Yr5t5EPlVabNkEp08uWvxpzyNg1bUNJeMVZi8Vf4IaoqaD1+r1qN5YoMDh6rhPvA5XokRtw==";
  private static final String USER_CANT_BE_FOUND_ERROR_MESSAGE = "User with this id can`t be found";
  private static final String USER_CANT_BE_DECRYPTED_ERROR_MESSAGE = "User full name can`t be decrypted correctly";
  private static final String DECRYPTED_FULL_USER_NAME = "Test Testov";
  private static final int EXISTENT_USER_ID = 1;
  private static final int NON_EXISTENT_USER_ID = 2;
  //  @Autowired
//  private MockMvc mockMvc;
  private UserNameService userNameService;
  private UserNameController testedInstance;

  @Before
  public void init() {
    userNameService = mock(UserNameService.class);
    when(userNameService.getEncryptedFullName(1)).thenReturn(ENCRYPTED_FULL_USER_NAME_CORRECT);
    UserNameCantBeObtainedException userCantBeFoundException = new UserNameCantBeObtainedException(USER_CANT_BE_FOUND_ERROR_MESSAGE);
    when(userNameService.getEncryptedFullName(2)).thenThrow(userCantBeFoundException);
    when(userNameService.getFullName(ENCRYPTED_FULL_USER_NAME_CORRECT)).thenReturn(DECRYPTED_FULL_USER_NAME);
    Exception encryptorException = new Exception();
    UserNameCantBeObtainedException userFullNameCantBeDecryptedException = new UserNameCantBeObtainedException(USER_CANT_BE_DECRYPTED_ERROR_MESSAGE, encryptorException);
    when(userNameService.getFullName(ENCRYPTED_FULL_USER_NAME_UNCORRECT)).thenThrow(userFullNameCantBeDecryptedException);
    testedInstance = new UserNameController(userNameService);
  }

  @Test
  public void shouldReturnCorrectResponseOnRequestWithCorrectId() {
    UserNameRequestDTO testRequest = new UserNameRequestDTO();
    testRequest.setId(EXISTENT_USER_ID);

    UserNameResponseDTO response = testedInstance.getEncryptedFullUserName(testRequest);

    verify(userNameService, times(1)).getEncryptedFullName(EXISTENT_USER_ID);
    verify(userNameService, times(1)).getEncryptedFullName(anyInt());
    assertEquals(ENCRYPTED_FULL_USER_NAME_CORRECT, response.getFullNameEncrypted());
  }

  @Test
  public void shouldThrowCorrectExceptionOnRequestWithIncorrectId() {
    UserNameRequestDTO testRequest = new UserNameRequestDTO();
    testRequest.setId(NON_EXISTENT_USER_ID);

    try {
      testedInstance.getEncryptedFullUserName(testRequest);
    } catch (Exception ex) {
      assertEquals(UserNameCantBeObtainedException.class, ex.getClass());
      assertEquals(USER_CANT_BE_FOUND_ERROR_MESSAGE, ex.getMessage());
      assertNull(ex.getCause());
    }

    verify(userNameService, times(1)).getEncryptedFullName(NON_EXISTENT_USER_ID);
    verify(userNameService, times(1)).getEncryptedFullName(anyInt());
  }

  @Test
  public void shouldReturnCorrectResponseOnRequestWithCorrectEncryptedName() {
    UserNameRequestDTO testRequest = new UserNameRequestDTO();
    testRequest.setEncryptedFullName(ENCRYPTED_FULL_USER_NAME_CORRECT);

    UserNameResponseDTO response = testedInstance.getDecryptedFullUserName(testRequest);

    verify(userNameService, times(1)).getFullName(ENCRYPTED_FULL_USER_NAME_CORRECT);
    verify(userNameService, times(1)).getFullName(anyString());
    assertEquals(DECRYPTED_FULL_USER_NAME, response.getFullName());
  }

  @Test
  public void shouldThrowCorrectExceptionOnRequestWithIncorrectEncryptedName() {
    UserNameRequestDTO testRequest = new UserNameRequestDTO();
    testRequest.setEncryptedFullName(ENCRYPTED_FULL_USER_NAME_UNCORRECT);

    try {
      testedInstance.getDecryptedFullUserName(testRequest);
    } catch (Exception ex) {
      assertEquals(UserNameCantBeObtainedException.class, ex.getClass());
      assertEquals(USER_CANT_BE_DECRYPTED_ERROR_MESSAGE, ex.getMessage());
      assertNotNull(ex.getCause());
    }

    verify(userNameService, times(1)).getFullName(ENCRYPTED_FULL_USER_NAME_UNCORRECT);
    verify(userNameService, times(1)).getFullName(anyString());
  }
}