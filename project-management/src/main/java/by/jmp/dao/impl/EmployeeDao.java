package by.jmp.dao.impl;


import by.jmp.dao.BaseDao;
import by.jmp.model.Employee;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class EmployeeDao extends BaseDao<Employee> {

    @Autowired @Override public void setClazz() {
        this.clazz = Employee.class;
    }

    public void addEmployeeToProject(Integer employeeId, Integer projectId) {

        Session session = sessionFactory.openSession();
        try {
            Query query = session.createSQLQuery("INSERT INTO employee_project(employee_id, project_id) VALUES (:employeeId, :projectId)");
            query.setParameter("employeeId", employeeId);
            query.setParameter("projectId", projectId);
            query.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void removeEmployeeToProject(Integer employeeId, Integer projectId) {

        Session session = sessionFactory.openSession();
        try {
            Query query = session.createSQLQuery("DELETE FROM employee_project WHERE employee_id = :employeeId AND project_id = :projectId");
            query.setParameter("employeeId", employeeId);
            query.setParameter("projectId", projectId);
            query.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
