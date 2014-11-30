package by.mentoring.data.impl;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import by.mentoring.data.PersonDao;
import by.mentoring.model.Person;

@Stateless
public class PersonDaoImpl extends GenericDaoImpl<Person> implements PersonDao {

  @SuppressWarnings("unused")
  private final EntityManager em;

  public  PersonDaoImpl() {
    super(null);
    this.em = null;
  }

  @Inject
  public PersonDaoImpl(EntityManager em) {
    super(em);
    this.em = em;
  }

}
