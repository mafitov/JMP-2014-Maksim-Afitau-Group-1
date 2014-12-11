package by.mentoring.ejb;

import by.mentoring.model.Account;
import by.mentoring.model.Currency;

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
public class CurrencyServiceBean implements CurrencyServiceLocal {
    @PersistenceContext
    private EntityManager em;

    public List<Currency> getAll() {
        Query query = em.createQuery("from Currency");
        return query.getResultList();
    }

    @Override
    public void saveOrUpdate(Currency currency) {
        em.merge(currency);
    }

    @Override
    public Currency get(Integer id) {
        return em.find(Currency.class, id);
    }
}
