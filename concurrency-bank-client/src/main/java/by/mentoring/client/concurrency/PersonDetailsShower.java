package by.mentoring.client.concurrency;

import by.mentoring.client.flows.PersonFlows;
import org.apache.log4j.Logger;

import java.io.IOException;

public class PersonDetailsShower implements Runnable {

  private static final Logger log = Logger.getLogger(PersonDetailsShower.class);

  private final PersonFlows personFlows = new PersonFlows();

  @Override
  public void run() {
    try {
      personFlows.showPersonDetails();
    } catch (IOException e) {

      log.error("Something happened! ", e);
    }
  }

}
