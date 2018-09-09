package org.kaa.moneytransfer.banking.entity;

public class AccountDoesNotExistException extends RuntimeException {
  public AccountDoesNotExistException(String message) {
    super(message);
  }

}
