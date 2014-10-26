package by.mentoring.service;

import by.mentoring.model.Person;
import org.codehaus.jackson.type.JavaType;

import java.util.List;

public class PersonService extends BaseService<Person> {

    public PersonService() {
        super("persons.json");
    }

    @Override
    protected JavaType getValueType() {
        return mapper.getTypeFactory().constructParametricType(List.class, Person.class);
    }
}
