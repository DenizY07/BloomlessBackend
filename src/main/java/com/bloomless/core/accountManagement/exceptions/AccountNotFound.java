package com.bloomless.core.accountManagement.exceptions;

public class AccountNotFound extends RuntimeException {
  public AccountNotFound(String message) {
    super(message);
  }
}
