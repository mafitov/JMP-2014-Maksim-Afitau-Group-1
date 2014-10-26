package by.mentoring.service;

import by.mentoring.model.Account;
import org.codehaus.jackson.type.JavaType;

import java.util.List;

public class AccountService extends BaseService<Account> {

    public AccountService() {
        super("accounts.json");
    }

    @Override
    protected JavaType getValueType() {
        return mapper.getTypeFactory().constructParametricType(List.class, Account.class);
    }
}
