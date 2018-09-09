package org.kaa.moneytransfer.control;

import org.kaa.moneytransfer.entity.Account;
import org.kaa.moneytransfer.entity.Operation;

import java.util.List;

public class BankingService {
  private AccountService accountService;
  private OperationService operationService;

  public BankingService(AccountService accountService, OperationService operationService) {
    this.accountService = accountService;
    this.operationService = operationService;
  }

  public void transfer(long from, long to, long amount){
    validate(amount);
    Account fromAccount = getAccount(from);
    Account toAccount = getAccount(to);
    fromAccount.withdraw(amount);
    toAccount.deposit(amount);
    operationService.log(new Operation(from, to, amount));
  }

  public Account getAccount(long id){
    return accountService.getAccount(id);
  }

  public List<Operation> operations(){
    return operationService.getOperations();
  }

  private void validate(long value){
    if(value < 0){
      throw new IllegalArgumentException("Amount can not be less then 0.");
    }
  }
}
