package org.kaa.moneytransfer.control;

import org.kaa.moneytransfer.entity.Account;
import org.kaa.moneytransfer.entity.NotEnoughMoneyException;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static java.lang.String.format;

public class BankingService {
  private Lock lock;
  private AccountService accountService;

  public BankingService(AccountService accountService) {
    this.accountService = accountService;
    this.lock = new ReentrantLock();
  }

  public void transfer(long from, long to, long amount){
    validate(amount);
    try{
      Account fromAccount = accountService.getAccount(from);
      long fromAmount = fromAccount.getAmount();
      if(fromAmount < amount){
        throw new NotEnoughMoneyException(format("Account %d have amount %d. We can not transfer %d amount.", from, fromAmount, amount));
      }
      Account toAccount = accountService.getAccount(to);
      lock.lock();
      fromAccount.withdraw(amount);
      toAccount.deposit(amount);
    } finally {
      lock.unlock();
    }
  }

  private void validate(long value){
    if(value < 0){
      throw new IllegalArgumentException("Amount can not be less then 0.");
    }
  }
}
