package by.mentoring.service;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.enterprise.inject.Produces;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Inject;
import javax.inject.Named;

import by.mentoring.data.BankDao;
import by.mentoring.model.Bank;

@ManagedBean
@Stateless
public class BankService {

  private final Logger log = Logger.getLogger("BankService");

  @Inject
  private BankDao bankDao;

  private List<Bank> banks;
  @Produces
  @Named
  public List<Bank> getBanks() {

    log.info("Show all banks");

    if (banks == null) {
      banks = bankDao.getAll();
    }
    return banks;
  }

  private Bank currentBank;

  public void preRender(ComponentSystemEvent event) {
    setCurrentBank(initCurrentBank());
  }


  private Bank initCurrentBank() {

    Long id = Long.valueOf(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id"));

    log.info("Find bank for id " + id);
    return bankDao.find(id);
  }

  public Bank getCurrentBank() {
    log.info("return current bank " + currentBank);
    return currentBank;
  }

  public void setCurrentBank(Bank currentBank) {
    log.info("set current bank");
    this.currentBank = currentBank;
  }

}
