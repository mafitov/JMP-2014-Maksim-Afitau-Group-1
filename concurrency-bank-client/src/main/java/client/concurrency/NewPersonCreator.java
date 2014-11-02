package client.concurrency;

import client.flows.PersonFlows;
import org.apache.log4j.Logger;

import java.io.IOException;

public class NewPersonCreator implements Runnable {

  private static final Logger log = Logger.getLogger(NewPersonCreator.class);

  private final PersonFlows personFlows = new PersonFlows();

  @Override
  public void run() {
    try {
      personFlows.createNewPerson();
    } catch (IOException e) {

      log.error("Something happened! ", e);
    }
  }

}
