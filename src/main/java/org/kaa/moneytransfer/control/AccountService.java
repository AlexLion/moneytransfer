package org.kaa.moneytransfer.control;

import org.kaa.moneytransfer.entity.Account;
import org.kaa.moneytransfer.entity.AccountDoesNotExistException;


import static java.lang.String.format;

public class AccountService {
  private AccountDao accountDao;

  public AccountService(AccountDao accountDao){
    this.accountDao = accountDao;
  }

  public Account getAccount(long id){
    return accountDao
        .find(id)
        .orElseThrow(() -> new AccountDoesNotExistException(format("Can not find account with id: %d", id)));
  }
}
