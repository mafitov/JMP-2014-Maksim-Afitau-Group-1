package by.mentoring.data;

import java.util.List;

public interface GenericDao<T> {

  public void save(T object);

  public List<T> getAll();

  public T update(T t);

  public T find(Long id);

}
