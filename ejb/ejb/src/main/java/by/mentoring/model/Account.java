package by.mentoring.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Account extends BaseStoreObject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;
    @Basic
    private Integer currencyId;
    @Basic
    private BigDecimal amount;

    public Account() {}

    public Account(Integer id, Integer currencyId, BigDecimal amount) {
        super(id);

        this.currencyId = currencyId;
        this.amount = amount;
    }

    public Account(Account account) {
        super(account.id);

        this.currencyId = account.currencyId;
        this.amount = account.amount;
    }

    public Account(Integer currencyId, BigDecimal amount) {
        this.currencyId = currencyId;
        this.amount = amount;
    }

    public Integer getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(Integer currencyId) {
        this.currencyId = currencyId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return  new StringBuilder().append(id).append(";").append(currencyId).append(";").append(amount).toString();
    }

}
