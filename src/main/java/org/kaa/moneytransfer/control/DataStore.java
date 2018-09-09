package org.kaa.moneytransfer.control;

import org.kaa.moneytransfer.entity.Account;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class DataStore {

  Map<Long, Account> accountMap;

  public DataStore(){
    this(initMap());
  }

  public DataStore(Map<Long, Account> accountMap){
    this.accountMap = accountMap;
  }

  public Optional<Account> find(long id){
    return Optional.ofNullable(accountMap.get(id));
  }

  private static Map<Long, Account>  initMap(){
    Map<Long, Account>  accountMap = new HashMap<>();
    accountMap.put(1L, new Account(1, 1000));
    accountMap.put(2L, new Account(2, 2000));
    accountMap.put(3L, new Account(3, 3000));
    accountMap.put(4L, new Account(4, 4000));
    accountMap.put(5L, new Account(5, 5000));
    accountMap.put(6L, new Account(6, 6000));
    accountMap.put(7L, new Account(7, 7000));
    return accountMap;
  }
}
