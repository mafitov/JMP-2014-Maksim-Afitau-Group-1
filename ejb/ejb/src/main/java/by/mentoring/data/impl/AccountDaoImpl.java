package by.mentoring.data.impl;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import by.mentoring.data.AccountDao;
import by.mentoring.model.Account;


@Stateless
public class AccountDaoImpl extends GenericDaoImpl<Account> implements AccountDao {

  public AccountDaoImpl(){}

  @Inject
  public AccountDaoImpl(EntityManager em) {
    super(em);
  }

}
