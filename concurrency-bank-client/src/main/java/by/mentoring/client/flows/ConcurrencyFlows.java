package by.mentoring.client.flows;

import by.mentoring.client.concurrency.MoneyExchange;
import by.mentoring.client.concurrency.MoneyExchangeFileSync;
import by.mentoring.client.concurrency.MoneyExchangeSync;
import by.mentoring.model.Account;
import by.mentoring.service.AccountService;
import com.google.common.collect.Lists;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ConcurrencyFlows {

  private final AccountService accountService = new AccountService();

  private static final Logger log = Logger.getLogger(ConcurrencyFlows.class);

  public void runSyncDemo() throws InterruptedException {

    List<Account> accounts = Lists.newArrayList();
    accounts.add(new Account(1, 1, new BigDecimal("1000")));
    accounts.add(new Account(2, 1, new BigDecimal("1000")));
    accounts.add(new Account(3, 1, new BigDecimal("1000")));
    accounts.add(new Account(4, 1, new BigDecimal("1000")));
    accounts.add(new Account(5, 1, new BigDecimal("1000")));
    accounts.add(new Account(6, 1, new BigDecimal("1000")));
    accounts.add(new Account(7, 1, new BigDecimal("1000")));
    accounts.add(new Account(8, 1, new BigDecimal("1000")));
    accounts.add(new Account(9, 1, new BigDecimal("1000")));
    accounts.add(new Account(10, 1, new BigDecimal("1000")));

    ExecutorService executor = Executors.newCachedThreadPool();

    MoneyExchangeSync syncExchanger = new MoneyExchangeSync(accounts);

    executor.execute(syncExchanger);
    executor.execute(syncExchanger);
    executor.execute(syncExchanger);
    executor.execute(syncExchanger);
    executor.execute(syncExchanger);
    executor.execute(syncExchanger);
    executor.execute(syncExchanger);
    executor.execute(syncExchanger);
    executor.execute(syncExchanger);
    executor.execute(syncExchanger);

    executor.shutdown();

    Thread.sleep(3000);

    Double total = 0d;
    for (Account account : accounts) {
      total += account.getAmount().doubleValue();
      log.info("account " + account);
    }

    log.info("Total amount: " + total);
  }

  public void runCuncDemo() throws InterruptedException {

    List<Account> accounts = Lists.newArrayList();
    accounts.add(new Account(1, 1, new BigDecimal("1000")));
    accounts.add(new Account(2, 1, new BigDecimal("1000")));
    accounts.add(new Account(3, 1, new BigDecimal("1000")));
    accounts.add(new Account(4, 1, new BigDecimal("1000")));
    accounts.add(new Account(5, 1, new BigDecimal("1000")));
    accounts.add(new Account(6, 1, new BigDecimal("1000")));
    accounts.add(new Account(7, 1, new BigDecimal("1000")));
    accounts.add(new Account(8, 1, new BigDecimal("1000")));
    accounts.add(new Account(9, 1, new BigDecimal("1000")));
    accounts.add(new Account(10, 1, new BigDecimal("1000")));

    ExecutorService executor = Executors.newCachedThreadPool();

    MoneyExchange exchanger = new MoneyExchange(accounts);

    executor.execute(exchanger);
    executor.execute(exchanger);
    executor.execute(exchanger);
    executor.execute(exchanger);
    executor.execute(exchanger);
    executor.execute(exchanger);
    executor.execute(exchanger);
    executor.execute(exchanger);
    executor.execute(exchanger);
    executor.execute(exchanger);

    executor.shutdown();

    Thread.sleep(3000);

    Double total = 0d;
    for (Account account : accounts) {
      total += account.getAmount().doubleValue();
      log.info("account " + account);
    }

    log.info("Total amount: " + total);
  }

  public void fileExchangerDemo() throws IOException, InterruptedException {

    ExecutorService executor = Executors.newCachedThreadPool();

    MoneyExchangeFileSync exchanger = new MoneyExchangeFileSync();

    executor.execute(exchanger);
    executor.execute(exchanger);
    executor.execute(exchanger);
    executor.execute(exchanger);
    executor.execute(exchanger);
    executor.execute(exchanger);
    executor.execute(exchanger);
    executor.execute(exchanger);
    executor.execute(exchanger);
    executor.execute(exchanger);

    executor.shutdown();

    Thread.sleep(5000);

    List<Account> accounts = accountService.getAll();

    Double total = 0d;
    for (Account account : accounts) {
      total += account.getAmount().doubleValue();
      log.info("account " + account);
    }

    log.info("Total amount: " + total);

  }

}
