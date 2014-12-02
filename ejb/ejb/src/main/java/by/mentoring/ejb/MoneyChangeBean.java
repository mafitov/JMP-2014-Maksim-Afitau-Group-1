package by.mentoring.ejb;

import by.mentoring.model.Account;

import javax.annotation.security.DeclareRoles;
import javax.ejb.*;
import javax.inject.Inject;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.concurrent.ThreadLocalRandom;

@Stateful
public class MoneyChangeBean implements MoneyChangeLocal {
    @Inject
    PersonServiceBean personServiceBean;
    @Inject
    CurrencyServiceBean currencyServiceLocal;
    @Inject
    AccountServiceBean accountServiceLocal;

    @Override
    public void change(Integer from, Integer to, BigDecimal amount) {

        Account accountFrom = accountServiceLocal.get(from);
        Account accountTo = accountServiceLocal.get(to);

        Boolean selectedAccountsCorrect = accountTo != null && accountFrom != null && from != to;
        if (selectedAccountsCorrect) {

            Boolean transferAllowed = accountFrom.getAmount().subtract(amount).doubleValue() > 0;
            if (transferAllowed) {

                transferAmount(amount, accountFrom, accountTo);

            }

        }
        accountServiceLocal.saveOrUpdate(accountFrom);
        accountServiceLocal.saveOrUpdate(accountTo);
    }

    private void transferAmount(BigDecimal amount, Account from, Account to) {

        BigDecimal prevAmountFrom = from.getAmount();
        BigDecimal prevAmountTo = to.getAmount();

        from.setAmount(prevAmountFrom.subtract(amount).setScale(2, RoundingMode.CEILING));
        to.setAmount(prevAmountTo.add(amount).setScale(2, RoundingMode.CEILING));
    }
}
