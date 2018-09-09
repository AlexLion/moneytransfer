package org.kaa.moneytransfer.banking.control;

import org.junit.jupiter.api.Test;
import org.kaa.moneytransfer.banking.entity.Account;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BankingServiceTest {

  private static final int ITERATION_COUNT = 100_000;
  private static final int JOB_AMOUNT = 1;
  private static final int THREAD_COUNT_HARD = 20;
  private static final int THREAD_COUNT_SOFT = 2;

  @Test
  void transferHard() throws InterruptedException {
    // given
    ExecutorService executorService = Executors.newFixedThreadPool(THREAD_COUNT_HARD);
    final int INITIAL_AMOUNT = 50_000;
    CountDownLatch startingLatch = new CountDownLatch(THREAD_COUNT_HARD);
    CountDownLatch finishingLatch = new CountDownLatch(THREAD_COUNT_HARD);
    Account account = new Account(1, INITIAL_AMOUNT);

    for(int i = 0; i < 10; i++){
      executorService.submit(new Withdraw(account, startingLatch, finishingLatch));
      executorService.submit(new Deposit(account, startingLatch, finishingLatch));
    }

    startingLatch.await();
    System.out.println("Start jobs.");
    finishingLatch.await();
    System.out.println("Finish jobs.");


    // then
    assertEquals(INITIAL_AMOUNT, account.getAmount());

  }

  @Test
  void transferSoft() throws InterruptedException {
    // given
    ExecutorService executorService = Executors.newFixedThreadPool(2);
    final int INITIAL_AMOUNT = 50_000;
    CountDownLatch startingLatch = new CountDownLatch(THREAD_COUNT_SOFT);
    CountDownLatch finishingLatch = new CountDownLatch(THREAD_COUNT_SOFT);
    Account account = new Account(1, INITIAL_AMOUNT);

    executorService.submit(new Thread(new Withdraw(account, startingLatch, finishingLatch)));
    executorService.submit(new Thread(new Deposit(account, startingLatch, finishingLatch)));

    // when
    startingLatch.await();
    System.out.println("Start jobs.");
    finishingLatch.await();
    System.out.println("Finish jobs.");

    // then
    assertEquals(INITIAL_AMOUNT, account.getAmount());
  }

  private static class Withdraw implements Runnable{

    private final Account account;
    private CountDownLatch startingLatch;
    private CountDownLatch finishingLatch;

    Withdraw(Account account, CountDownLatch startingLatch, CountDownLatch finishingLatch){
      this.account = account;
      this.startingLatch = startingLatch;
      this.finishingLatch = finishingLatch;
    }

    @Override
    public void run() {
      startingLatch.countDown();
      try{
        for(int i = 1; i < ITERATION_COUNT; i++){
          account.withdraw(JOB_AMOUNT);
//          System.out.println("withdraw. amount = " + account.getAmount());
        }
      } finally{
        finishingLatch.countDown();
      }
    }
  }

  private static class Deposit implements Runnable{

    private final Account account;
    private CountDownLatch startingLatch;
    private CountDownLatch finishingLatch;

    Deposit(Account account, CountDownLatch startingLatch, CountDownLatch finishingLatch){
      this.account = account;
      this.startingLatch = startingLatch;
      this.finishingLatch = finishingLatch;
    }

    @Override
    public void run() {
      startingLatch.countDown();
      try {
        for (int i = 1; i < ITERATION_COUNT; i++) {
          account.deposit(JOB_AMOUNT);
//          System.out.println("deposit. amount = " + account.getAmount());
        }
      } finally{
        finishingLatch.countDown();
      }
    }
  }
}