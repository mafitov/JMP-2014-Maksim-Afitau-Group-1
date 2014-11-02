package client.flows;

import by.mentoring.model.Account;
import by.mentoring.model.Currency;
import by.mentoring.service.AccountService;
import by.mentoring.service.CurrencyServie;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.List;

public class AccountFlows {

  private static final Logger log = Logger.getLogger(AccountFlows.class);

  private final AccountService accountService = new AccountService();
  private final CurrencyServie currencyService = new CurrencyServie();

  public void createAccount() throws IOException {

    log.info("What is account currency: 1 - br, 2 - us, 3 - eu:");
    String currencyIdStr = new BufferedReader(new InputStreamReader(System.in)).readLine();

    Integer currencyId = Integer.valueOf(currencyIdStr);

    log.info("What is account amount? ");
    String accountAmount = new BufferedReader(new InputStreamReader(System.in)).readLine();

    BigDecimal amount = new BigDecimal(accountAmount);

    Account newAccount = new Account(currencyId, amount);

    accountService.saveOrUpdate(newAccount);

    log.info("Account created!");
  }

  public void showAllAccounts() throws IOException {

    List<Account> accounts = accountService.getAll();

    log.info("Accounts list:");
    for(Account account: accounts) {
        Currency currency = currencyService.get(account.getCurrencyId());
      log.info("Account: " + account.getId() + " amount " + account.getAmount() + " currency " + currency.getName());
    }
  }

}
