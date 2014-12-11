package by.mentoring.ejb;

import by.mentoring.model.Account;

import javax.ejb.Local;
import java.util.List;

@Local
public interface AccountServiceLocal {
    public List<Account> getAll();
    public void saveOrUpdate(Account account);
    public Account get(Integer id);
}
