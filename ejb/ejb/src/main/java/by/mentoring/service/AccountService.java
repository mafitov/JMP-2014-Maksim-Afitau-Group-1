package by.mentoring.service;

import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJBException;
import javax.ejb.Stateful;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import by.mentoring.data.AccountDao;
import by.mentoring.data.BankDao;
import by.mentoring.model.Account;
import by.mentoring.model.Bank;
import by.mentoring.model.Person;

@Stateful
@Model
public class AccountService {

  private final Logger log = Logger.getLogger("AccountService");

  @Inject
  private AccountDao accountDao;

  @Inject
  private BankDao bankDao;

  private Account newAccount;
  private Person newPerson;
  private List<Account> accounts;

  private String selectedBankId;

  @Produces
  @Named
  public Account getNewAccount() {
    return newAccount;
  }

  @Produces
  @Named
  public Person getNewPerson() {
    return newPerson;
  }

  @Produces
  @Named
  public List<Account> getAccounts() {
    return accounts;
  }

  public void createNewAccount() {

    log.info("Create new account for bank with id " + selectedBankId);

    String newAccountNumber = newAccount.getAccountNumber();
    try {

      Bank bankForAccount = bankDao.find(Long.valueOf(selectedBankId));

      newAccount.setPerson(newPerson);
      newAccount.setBank(bankForAccount);
      accountDao.save(newAccount);

      refreshModels();
    } catch (EJBException e) {

      e.printStackTrace();
      handleException("Error occured while saving account with id " + newAccountNumber);
    }
  }


  @PostConstruct
  public void refreshModels() {
    newAccount = new Account();
    newPerson = new Person();

    this.accounts = accountDao.getAll();
  }

  private void handleException(String message) {

    FacesMessage fMessage = new FacesMessage(message);
    FacesContext.getCurrentInstance().addMessage("new-account:acc-number", fMessage);
  }

  public String getSelectedBankId() {
    return selectedBankId;
  }

  public void setSelectedBankId(String selectedBankId) {
    log.info("selected bank " + selectedBankId);
    this.selectedBankId = selectedBankId;
  }

}
