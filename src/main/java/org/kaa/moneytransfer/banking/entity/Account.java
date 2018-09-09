package org.kaa.moneytransfer.banking.entity;

import lombok.Getter;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static java.lang.String.format;


public class Account {
  @Getter
  private long id;
  @Getter
  private volatile long amount;
  private Lock lock;

  public Account(long id){
    this(id, 0);
  }

  public Account(long id, long amount){
    this.id = id;
    this.amount = amount;
    this.lock = new ReentrantLock();
  }

  public void withdraw(long amount){
    lock.lock();
    try {
      if (this.amount < amount) {
        throw new NotEnoughMoneyException(format("Account %d have amount %d. We can not transfer %d amount.", id, this.amount, amount));
      }
      this.amount -= amount;
    } finally {
      lock.unlock();
    }
  }

  public void deposit(long amount){
    lock.lock();
    this.amount += amount;
    lock.unlock();
  }

}
