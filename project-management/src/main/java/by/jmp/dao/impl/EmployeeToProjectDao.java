package by.jmp.dao.impl;

import by.jmp.dao.BaseDao;
import by.jmp.model.EmployeeToProject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeToProjectDao extends BaseDao<EmployeeToProject> {

    @Autowired
    @Override public void setClazz() {
        this.clazz = EmployeeToProject.class;
    }
}
