package by.jmp.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseDao<T> implements CrudDao<T> {

    protected Class<T> clazz;

    @Autowired
    protected SessionFactory sessionFactory;

    @Override public void saveOrUpdate(T object) {
        Session session = sessionFactory.openSession();
        try {
            session.save(object);
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

    }

    @Override public void delete(T object) {
        Session session = sessionFactory.openSession();
        try {
            session.delete(object);
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override public T getById(Integer id) {
        Session session = sessionFactory.openSession();
        try {
            return (T) session.get(clazz, id);
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

    public abstract void setClazz();
}
