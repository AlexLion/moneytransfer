package org.kaa.moneytransfer.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static java.lang.String.format;

@Getter
@Setter
public class Account {
  private long id;
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
    if(this.amount < amount){
      throw new NotEnoughMoneyException(format("Account %d have amount %d. We can not transfer %d amount.", id, this.amount, amount));
    }
    this.amount -= amount;
    lock.unlock();
  }

  public void deposit(long amount){
    lock.lock();
    this.amount += amount;
    lock.unlock();
  }

}
