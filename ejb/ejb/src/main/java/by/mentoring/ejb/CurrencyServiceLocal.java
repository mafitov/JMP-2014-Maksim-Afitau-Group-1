package by.mentoring.ejb;

import by.mentoring.model.Account;
import by.mentoring.model.Currency;

import javax.ejb.Local;
import java.util.List;

@Local
public interface CurrencyServiceLocal {
    public List<Currency> getAll();
    public void saveOrUpdate(Currency currency);
    public Currency get(Integer id);
}
