package by.jmp.dao;


public interface CrudDao<T> {
    public void saveOrUpdate(T object);
    public void delete(T object);
    public T getById(Integer id);
}
