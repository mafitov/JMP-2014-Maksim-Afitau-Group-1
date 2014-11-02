package by.mentoring.client.concurrency;

import by.mentoring.client.flows.AccountFlows;
import org.apache.log4j.Logger;

import java.io.IOException;

public class NewAccountCreator implements Runnable {

  private static final Logger log = Logger.getLogger(NewAccountCreator.class);

  private final AccountFlows accountFlows = new AccountFlows();

  @Override
  public void run() {
    try {
      accountFlows.createAccount();
    } catch (IOException e) {

      log.error("Something happened! ", e);
    }
  }

}
