package by.mentoring.model;

import javax.persistence.*;

@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;

    @Basic
    private String name;
    @Basic
    private Integer accountId;

    public Person() {}

    public Person(String name, Integer accountId) {
        this.name = name;
        this.accountId = accountId;
    }

    public Person(Integer id, String name, Integer accountId) {
        this.id = id;
        this.name = name;
        this.accountId = accountId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
     this.name = name;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
    Integer selectedAccountId = accountId != null ? accountId : 0;
        return  new StringBuilder().append(id).append(";").append(name).append(";").append(selectedAccountId).toString();
    }

}
