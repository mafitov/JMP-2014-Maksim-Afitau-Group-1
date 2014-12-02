package by.mentoring.ejb;

import by.mentoring.model.Person;

import javax.ejb.Local;
import java.util.List;

@Local
public interface PersonServiceLocal {
    public List<Person> getAll();
    public void saveOrUpdate(Person person);
    public Person get(Integer id);
}
