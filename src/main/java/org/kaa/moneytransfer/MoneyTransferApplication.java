package org.kaa.moneytransfer;

import io.dropwizard.Application;
import io.dropwizard.setup.Environment;
import org.kaa.moneytransfer.banking.boundary.BankingResourse;
import org.kaa.moneytransfer.banking.boundary.mapper.AccountDoesNotExistExceptionMapper;
import org.kaa.moneytransfer.banking.boundary.mapper.MainExceptionMapper;
import org.kaa.moneytransfer.banking.boundary.mapper.NotEnoughMoneyExceptionMapper;
import org.kaa.moneytransfer.banking.control.AccountService;
import org.kaa.moneytransfer.banking.control.BankingService;
import org.kaa.moneytransfer.banking.control.DataStore;
import org.kaa.moneytransfer.banking.control.OperationService;

public class MoneyTransferApplication extends Application<MoneyTransferConfiguration> {

  public static void main(String[] args) throws Exception {
    new MoneyTransferApplication().run(args);
  }

  @Override
  public void run(MoneyTransferConfiguration configuration, Environment environment) throws Exception {
    final BankingResourse resourse = new BankingResourse(new BankingService(new AccountService(new DataStore()), new OperationService()));
    environment.jersey().register(resourse);
    environment.jersey().register(new MainExceptionMapper());
    environment.jersey().register(new AccountDoesNotExistExceptionMapper());
    environment.jersey().register(new NotEnoughMoneyExceptionMapper());
  }
}
