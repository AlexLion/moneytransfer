package org.kaa.moneytransfer.banking.entity;

public class NotEnoughMoneyException extends RuntimeException {
  public NotEnoughMoneyException(String message) {
    super(message);
  }

}
