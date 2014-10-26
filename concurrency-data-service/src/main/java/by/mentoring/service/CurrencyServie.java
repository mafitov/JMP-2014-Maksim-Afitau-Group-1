package by.mentoring.service;

import by.mentoring.model.Account;
import by.mentoring.model.Currency;
import org.codehaus.jackson.type.JavaType;

import java.util.List;

public class CurrencyServie extends BaseService<Currency> {

    public CurrencyServie() {
        super("currency.json");
    }

    @Override
    protected JavaType getValueType() {
        return mapper.getTypeFactory().constructParametricType(List.class, Account.class);
    }
}
