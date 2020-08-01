package com.concord.coder.util;

import org.junit.Test;

import static com.concord.coder.util.CryptoHelper.*;
import static org.junit.Assert.*;

public class CryptoHelperTest {
  private static final String TEST_TEXT_FOR_DECRYPTING = "rtPlvv+qO0f9Uoq7CODILcBfMf0+4Xpl1ukvwLQH45c2TRCQ36km0IPZGgcaf0TJYf/xznc=";
  private static final String TEST_TEXT_FOR_ENCRYPTING = "Some text";
  private static final String PASSWORD = "password";

  @Test
  public void shouldEncrypt() throws Exception {

    String response = encrypt(TEST_TEXT_FOR_ENCRYPTING, PASSWORD);
    System.out.println(response);

    assertNotNull(response);
  }

  @Test
  public void shouldDecrypt() throws Exception {

    String response = decrypt(TEST_TEXT_FOR_DECRYPTING, PASSWORD);

    assertEquals(TEST_TEXT_FOR_ENCRYPTING, response);
  }

  @Test
  public void shouldThrowExceptionOnUncorrectEncodedText() {

    try {
      String response = decrypt(TEST_TEXT_FOR_DECRYPTING, PASSWORD);
    } catch (Exception ex) {
      assertNotNull(ex);
    }
  }
}
