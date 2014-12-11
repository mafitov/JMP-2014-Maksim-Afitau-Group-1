package by.mentoring.model;

import javax.persistence.*;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Currency extends BaseStoreObject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;
    @Basic
    private String name;
    @Basic
    private Double multiplier;

    public Currency() {}

    public Currency(String name, Double multiplier) {
        this.name = name;
        this.multiplier = multiplier;
    }


    public Currency(Integer id, String name, Double multiplier) {
        super(id);

        this.name = name;
        this.multiplier = multiplier;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getMultiplier() {
        return multiplier;
    }

    public void setMultiplier(Double multiplier) {
        this.multiplier = multiplier;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return  new StringBuilder().append(id).append(";").append(name).append(";").append(multiplier).toString();
    }
}
