package by.mentoring.service;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.enterprise.inject.Produces;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Inject;
import javax.inject.Named;

import by.mentoring.data.AccountDao;
import by.mentoring.data.CurrencyDao;
import by.mentoring.model.Account;
import by.mentoring.model.Currency;

@ManagedBean
@ViewScoped
@TransactionManagement(TransactionManagementType.CONTAINER)
public class AccountCrudService {

  private final Logger log = Logger.getLogger("AccountService");

  @Inject
  private AccountDao accountDao;

  @Inject
  private CurrencyDao currencyDao;

  private Long currentAccountId;

  private Account currentAccount;


  private List<Currency> allCurrencies;
  @Produces
  @Named
  public List<Currency> getAllCurrencies() {

    log.info("Called all currencies...");

    if (allCurrencies == null) {
      allCurrencies = currencyDao.getAll();
    }

    return allCurrencies;
  }


  public String updateCurrentAccount(Account accountForUpdate) {

    currentAccountId = accountForUpdate.getId();

    log.info("Update account with id updated " + currentAccountId);
    accountDao.save(accountForUpdate);

    return "success?faces-redirect=true&includeViewParams=true";
  }


  public void preRender(ComponentSystemEvent event) {
    log.info("account prerender called");
    setCurrentAccount(initCurrentAccount());
  }


  private Account initCurrentAccount() {

    Account account = null;

    String idStr = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
    log.info("idStr " + idStr + " currentAccountId " + currentAccountId);
    if (idStr != null || currentAccountId != null) {
      currentAccountId = currentAccountId != null ? currentAccountId : Long.valueOf(idStr);
      account = accountDao.find(currentAccountId);
    }

    log.info("Find account for id " + currentAccountId);
    return account;
  }


  @Named
  public Account getCurrentAccount() {
    log.info("currentAccount is null " + currentAccount);
    return currentAccount;
  }


  public void setCurrentAccount(Account currentAccount) {
    this.currentAccount = currentAccount;
  }


  public Long getCurrentAccountId() {
    return currentAccountId;
  }


  public void setCurrentAccountId(Long currentAccountId) {
    this.currentAccountId = currentAccountId;
  }

}
