package by.jmp.dao.impl;

import by.jmp.dao.BaseDao;
import by.jmp.model.Employee;
import by.jmp.model.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ProjectDao extends BaseDao<Project> {

    @Autowired
    @Override public void setClazz() {
        this.clazz = Project.class;
    }
}
