package org.kaa.moneytransfer.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Account {
  private long id;
  private volatile long amount;

  public Account(long id){
    this(id, 0);
  }

  public Account(long id, long amount){
    this.id = id;
    this.amount = amount;
  }

  public void withdraw(long amount){
    this.amount -= amount;
  }

  public void deposit(long amount){
    this.amount += amount;
  }

}
