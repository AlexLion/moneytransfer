package org.kaa.moneytransfer.control;

import org.kaa.moneytransfer.entity.Account;
import org.kaa.moneytransfer.entity.AccountDoesNotExistException;

import static java.lang.String.format;

public class AccountService {

  private DataStore dataStore;

  public AccountService(DataStore dataStore){
    this.dataStore = dataStore;
  }

  public Account getAccount(long id){
    return dataStore
        .find(id)
        .orElseThrow(() -> new AccountDoesNotExistException(format("Can not find account with id: %d", id)));
  }
}
