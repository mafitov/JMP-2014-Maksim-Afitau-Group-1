package by.mentoring.data.impl;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import by.mentoring.data.CurrencyDao;
import by.mentoring.model.Currency;


@Stateless
public class CurrencyDaoImpl extends GenericDaoImpl<Currency> implements CurrencyDao {

  public CurrencyDaoImpl(){}

  @Inject
  public CurrencyDaoImpl(EntityManager em) {
    super(em);
  }

}
