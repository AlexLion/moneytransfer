package org.kaa.moneytransfer.entity;

public class AccountDoesNotExistException extends RuntimeException {
  public AccountDoesNotExistException(String message) {
    super(message);
  }

}
