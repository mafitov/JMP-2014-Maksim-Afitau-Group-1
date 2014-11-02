package by.mentoring.client.flows;

import by.mentoring.model.Account;
import by.mentoring.model.Currency;
import by.mentoring.model.Person;
import by.mentoring.service.AccountService;
import by.mentoring.service.CurrencyServie;
import by.mentoring.service.PersonService;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.sun.corba.se.impl.io.ValueUtility;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.List;

public class PersonFlows {

    private static final Logger log = Logger.getLogger(PersonFlows.class);

    private PersonService personService;
    private CurrencyServie currencyService;
    private AccountService accountService;

    public PersonFlows() {
        personService = new PersonService();
        currencyService = new CurrencyServie();
        accountService = new AccountService();
    }

    public void createNewPerson() throws IOException {

        log.info("What is person's name? ");
        String name = new BufferedReader(new InputStreamReader(System.in)).readLine();

        log.info("Account id?");
        String accountId = new BufferedReader(new InputStreamReader(System.in)).readLine();

        personService.saveOrUpdate(new Person(name, Integer.valueOf(accountId)));

        log.info("Person created!");
    }

    public void showAllPersons() throws IOException {

        List<Person> persons = personService.getAll();

        log.info("List of persons: ");
        for (Person person : persons) {
            log.info(person.getId() + " " + person.getName() + " " + person.getAccountId());
        }
    }

    public void showPersonDetails() throws IOException {

        log.info("Enter person id: ");

        String personId = new BufferedReader(new InputStreamReader(System.in)).readLine();

        showPersonDetails(Integer.valueOf(personId));
    }

    private void showPersonDetails(Integer personId) throws IOException {

        Person curPerson = personService.get(personId);

        Account personAccount = null;
        if (curPerson != null) {

            personAccount = accountService.get(curPerson.getAccountId());

            String accountInfo = "Undefined";
            if (personAccount != null) {
                accountInfo = personAccount.getAmount().toString();

                Currency personCurrency = currencyService.get(personAccount.getCurrencyId());
                accountInfo += " " + personCurrency.getName();
            }

            log.info("Person details: ");
            log.info("First name: " + curPerson.getName());
            log.info("Account: " + accountInfo);

        } else {
            log.info("Person with id " + personId + " not found");
        }

        log.info("Want to convert money? (y:n): ");
        String decision = new BufferedReader(new InputStreamReader(System.in)).readLine();
        if (decision.equals("y")) {
            changeCurrency(personAccount);
        }
    }

    private void changeCurrency(Account account) throws IOException {

        Preconditions.checkNotNull(account, "Account could be bull");

        log.info("Select new currency: 1 - br, 2 - us, 3 - eu");

        String newCurrency = new BufferedReader(new InputStreamReader(System.in)).readLine();

        switch (newCurrency) {
            case "1":
                changeAccountToCurrency(account, 1);
                break;
            case "2":
                changeAccountToCurrency(account, 2);
                break;
            case "3":
                changeAccountToCurrency(account, 3);
                break;
            default:
                log.warn("Incorrect choice! ");
        }
    }

    protected void changeAccountToCurrency(Account account, Integer currencyId) throws IOException {

        Currency newCurrency = currencyService.get(currencyId);
        Currency curCurrency = currencyService.get(account.getCurrencyId());

        BigDecimal oldAmount = account.getAmount();
        Double multiplier = curCurrency.getMultiplier() / newCurrency.getMultiplier();

        BigDecimal newAmount = oldAmount.multiply(new BigDecimal(multiplier));

        account.setAmount(newAmount);
        account.setCurrencyId(currencyId);

        accountService.saveOrUpdate(account);

        log.info("Account currency converted!");
    }


}
