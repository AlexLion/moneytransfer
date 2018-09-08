package org.kaa.moneytransfer.entity;

public class NotEnoughMoneyException extends RuntimeException {
  public NotEnoughMoneyException(String message) {
    super(message);
  }

}
