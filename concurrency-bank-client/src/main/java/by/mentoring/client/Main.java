package by.mentoring.client;

import by.mentoring.model.Currency;
import by.mentoring.service.CurrencyServie;
import by.mentoring.client.concurrency.*;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

  private static final Logger log = Logger.getLogger(Main.class);

  public static void main(String[] args) throws IOException {
      CurrencyServie s = new CurrencyServie();

      Currency currency = new Currency();
      currency.setMultiplier(3D);currency.setName("br");
      s.saveOrUpdate(currency);

    while(true) {

      showMenu();

      String selected = new BufferedReader(new InputStreamReader(System.in)).readLine();

      log.info("You selected: " + selected);

      switch (selected) {

        case "1": new Thread(new NewPersonCreator()).run(); break;
        case "2": new Thread(new AllPersonsShower()).run(); break;
        case "3": new Thread(new PersonDetailsShower()).run(); break;
        case "4": new Thread(new NewAccountCreator()).run(); break;
        case "5": new Thread(new AllAccountsShower()).run(); break;
        case "6": new Thread(new AllAccountsShower()).run(); break;
        case "7": new Thread(new AllAccountsShower()).run(); break;
        case "8": System.exit(0); break;
      }
    }
  }

  private static void showMenu() {

    log.info("Please make your choise: ");
    log.info("1. Add new person ");
    log.info("2. Show all persons ");
    log.info("3. Show person details ");
    log.info("4. Add new account ");
    log.info("5. Show all accounts ");
    log.info("6. Start demo sync ");
    log.info("7. Start demo crazy ");
    log.info("8. Exit ");
  }
}
