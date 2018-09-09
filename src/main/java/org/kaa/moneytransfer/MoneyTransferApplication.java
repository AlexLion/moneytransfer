package org.kaa.moneytransfer;

import io.dropwizard.Application;
import io.dropwizard.setup.Environment;
import org.kaa.moneytransfer.boundary.BankingResourse;
import org.kaa.moneytransfer.control.AccountService;
import org.kaa.moneytransfer.control.BankingService;
import org.kaa.moneytransfer.control.DataStore;
import org.kaa.moneytransfer.control.OperationService;

public class MoneyTransferApplication extends Application<MoneyTransferConfiguration> {

  public static void main(String[] args) throws Exception {
    new MoneyTransferApplication().run(args);
  }

  @Override
  public void run(MoneyTransferConfiguration configuration, Environment environment) throws Exception {
    final BankingResourse resourse = new BankingResourse(new BankingService(new AccountService(new DataStore()), new OperationService()));
    environment.jersey().register(resourse);
  }
}
