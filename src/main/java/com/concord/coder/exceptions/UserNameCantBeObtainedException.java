package com.concord.coder.exceptions;

public class UserNameCantBeObtainedException extends RuntimeException {

  public UserNameCantBeObtainedException(String message) {
    super(message);
  }

  public UserNameCantBeObtainedException(String message, Throwable cause) {
    super(message, cause);
  }
}
