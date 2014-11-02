package by.mentoring.client.concurrency;

import by.mentoring.client.flows.PersonFlows;
import org.apache.log4j.Logger;

import java.io.IOException;

public class AllPersonsShower implements Runnable {

  private static final Logger log = Logger.getLogger(AllPersonsShower.class);

  private final PersonFlows personFlows = new PersonFlows();

  @Override
  public void run() {
    try {
      personFlows.showAllPersons();
    } catch (IOException e) {

      log.error("Something happened! ", e);
    }
  }

}
