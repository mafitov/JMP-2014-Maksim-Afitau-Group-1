package by.mentoring.client.concurrency;

import by.mentoring.model.Account;
import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class MoneyExchangeSync implements Runnable {

  private static final Logger log = Logger.getLogger(MoneyExchangeSync.class);

  private final List<Account> accounts;

  private final Integer NUMBER_OF_OPERATIONS = 1000;
  private final Double MAX_AMOUNT_TO_TRANSFER = 100d;

  public MoneyExchangeSync(List<Account> accounts) {
    this.accounts = accounts;
  }

  @Override
  public void run() {

    for(int i = 0;  i  < NUMBER_OF_OPERATIONS; ++i) {

      checkAccountsAndTransfer();

    }
  }

  private synchronized void checkAccountsAndTransfer() {

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

  private synchronized void transferAmount(BigDecimal amount, Account from, Account to) {

    BigDecimal prevAmountFrom = from.getAmount();
    BigDecimal prevAmountTo = to.getAmount();

    from.setAmount(prevAmountFrom.subtract(amount).setScale(2, RoundingMode.CEILING));
    to.setAmount(prevAmountTo.add(amount).setScale(2, RoundingMode.CEILING));
  }

}
