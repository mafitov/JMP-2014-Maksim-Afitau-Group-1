package by.mentoring.model;

import org.codehaus.jackson.annotate.JsonProperty;

public class Person extends BaseStoreObject {

    @JsonProperty
    private String name;

    @JsonProperty
    private Integer accountId;

    public Person() {}

    public Person(String name, Integer accountId) {
        this.name = name;
        this.accountId = accountId;
    }

    public Person(Integer id, String name, Integer accountId) {
        super(id);

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

    @Override
    public String toString() {
    Integer selectedAccountId = accountId != null ? accountId : 0;
        return  new StringBuilder().append(id).append(";").append(name).append(";").append(selectedAccountId).toString();
    }

}
