package by.mentoring.ejb;

import by.mentoring.model.Account;
import by.mentoring.model.Person;

import javax.annotation.security.DeclareRoles;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class AccountServiceBean implements AccountServiceLocal {
    @PersistenceContext
    private EntityManager em;

    public List<Account> getAll() {
        Query query = em.createQuery("from Account");
        return query.getResultList();
    }

    @Override
    public void saveOrUpdate(Account account) {
        em.merge(account);
    }

    @Override
    public Account get(Integer id) {
        return em.find(Account.class, id);
    }
}
