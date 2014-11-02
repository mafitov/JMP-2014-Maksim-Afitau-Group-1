package by.mentoring.client.concurrency;

import by.mentoring.model.Account;
import by.mentoring.service.AccountService;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class MoneyExchangeFileSync implements Runnable {

  private static final Logger log = Logger.getLogger(MoneyExchangeFileSync.class);

  private final AccountService accountService = new AccountService();

  private final List<Account> accounts;

  private final Integer NUMBER_OF_OPERATIONS = 1000;
  private final Double MAX_AMOUNT_TO_TRANSFER = 100d;

  public MoneyExchangeFileSync() throws IOException {
    accounts = accountService.getAll();
  }

  public MoneyExchangeFileSync(List<Account> accounts) {
    this.accounts = accounts;
  }

  @Override
  public void run() {

    for(int i = 0;  i  < NUMBER_OF_OPERATIONS; ++i) {

      try {
        checkAccountsAndTransfer();
      } catch (IOException e) {
        log.error("Error while wrinting data to file");
      }

    }
  }

  private synchronized void checkAccountsAndTransfer() throws IOException {

    int accountIdFrom = ThreadLocalRandom.current().nextInt(0, accounts.size() - 1);
    int accountIdTo = ThreadLocalRandom.current().nextInt(0, accounts.size() - 1);
    BigDecimal amountToTransfer = new BigDecimal(ThreadLocalRandom.current().nextDouble(0, MAX_AMOUNT_TO_TRANSFER)).setScale(2, RoundingMode.CEILING);

    Account accountFrom = accounts.get(accountIdFrom);
    Account accountTo = accounts.get(accountIdTo);

    Boolean selectedAccountsCorrect = accountTo != null && accountFrom != null && accountFrom.getId() != accountTo.getId();
    if (selectedAccountsCorrect) {

      Boolean transferAllowed = accountFrom.getAmount().subtract(amountToTransfer).doubleValue() > 0;
      if (transferAllowed) {
        log.info("Transfer from " + accountFrom + " to " + accountTo + " amount " + amountToTransfer);

        transferAmount(amountToTransfer, accountFrom, accountTo);

        log.info("Transfered now: " + accountFrom + " to " + accountTo + " amount " + amountToTransfer);
      }
    } else {
      log.warn("Account for id " + accountIdTo + " is " + accountTo);
      log.warn("Account for id " + accountIdFrom + " is " + accountFrom);
    }

  }

  private synchronized void transferAmount(BigDecimal amount, Account from, Account to) throws IOException {

    BigDecimal prevAmountFrom = from.getAmount();
    BigDecimal prevAmountTo = to.getAmount();

    from.setAmount(prevAmountFrom.subtract(amount).setScale(2, RoundingMode.CEILING));
    to.setAmount(prevAmountTo.add(amount).setScale(2, RoundingMode.CEILING));

    accountService.saveOrUpdate(from);
    accountService.saveOrUpdate(to);
  }

}
