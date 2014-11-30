package by.mentoring.data.impl;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import by.mentoring.data.BankDao;
import by.mentoring.model.Bank;


@Stateless
public class BankDaoImpl extends GenericDaoImpl<Bank> implements BankDao {

  public BankDaoImpl() {}

  @Inject
  public BankDaoImpl(EntityManager em) {
    super(em);
  }

}
