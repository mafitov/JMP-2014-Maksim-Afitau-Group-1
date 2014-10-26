package by.mentoring.model;

import org.codehaus.jackson.annotate.JsonProperty;

public class BaseStoreObject {

    @JsonProperty
    protected Integer id;

    public BaseStoreObject() {}

    public BaseStoreObject(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
