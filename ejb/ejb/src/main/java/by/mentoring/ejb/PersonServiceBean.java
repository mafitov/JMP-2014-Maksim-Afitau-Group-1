package by.mentoring.ejb;

import by.mentoring.model.Person;

import javax.annotation.security.DeclareRoles;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class PersonServiceBean implements PersonServiceLocal {
    @PersistenceContext
    private EntityManager em;

    public List<Person> getAll() {
        Query query = em.createQuery("from Person");
        return query.getResultList();
    }

    @Override
    public void saveOrUpdate(Person person) {
        em.merge(person);
    }

    @Override
    public Person get(Integer id) {
        return em.find(Person.class, id);
    }
}
