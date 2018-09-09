package org.kaa.moneytransfer.control;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.kaa.moneytransfer.entity.Account;
import org.kaa.moneytransfer.entity.AccountDoesNotExistException;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.String.format;
import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AccountServiceTest {

  private static Map<Long, Account> accountMap;

  @BeforeAll
  static void init(){
    accountMap = new HashMap<>();
    accountMap.put(2L, new Account(2, 200));
    accountMap.put(16L, new Account(16, 1600));
    accountMap.put(256L, new Account(256, 25600));
  }

  @Test
  void getAccountSuccess() {
    // given
    AccountService accountService = new AccountService(new DataStore(accountMap));
    List<Long> ids = Arrays.asList(2L, 16L, 256L);

    // when
    List<Account> accounts = ids.stream()
        .map(id -> accountService.getAccount(id))
        .collect(toList());

    // then
    assertEquals(ids.size(), accounts.size());
    assertEquals(
        ids.stream()
            .mapToLong(Long::valueOf)
            .sum(),
        accounts.stream()
            .map(account -> account.getId())
            .mapToLong(Long::valueOf)
            .sum()
    );
    assertEquals(
        ids.stream()
            .map(num -> num * 100L)
            .mapToLong(Long::valueOf).sum(),
        accounts.stream()
            .map(account -> account.getAmount())
            .mapToLong(Long::valueOf).sum()
    );
  }

  @Test()
  void getAccountFailed() {
    // given
    AccountService accountService = new AccountService(new DataStore(accountMap));
    long wrongId = 666L;
    String message = format("Can not find account with id: %d", wrongId);

    // when
    Throwable exception = assertThrows(AccountDoesNotExistException.class, () -> {
      accountService.getAccount(wrongId);
    });

    // then
    assertEquals(message, exception.getMessage());
  }

}