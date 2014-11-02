package by.mentoring.client.flows;

import by.mentoring.model.Account;
import by.mentoring.model.Currency;
import by.mentoring.service.AccountService;
import by.mentoring.service.CurrencyServie;
import by.mentoring.service.PersonService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.math.BigDecimal;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class PersonFlowsTest {

    private static final int ACC_ID = 23;
    private static final int FROM_CUR_ID = 1;
    private static final int TO_CUR_ID = 2;
    private static final double FROM_CUR_MULTY = 2;
    private static final double TO_CUR_MULTY = 10;
    private static final BigDecimal AMMOUNT = BigDecimal.valueOf(5000);

    @Mock private PersonService personService;
    @Mock private CurrencyServie currencyService;
    @Mock private AccountService accountService;

    @InjectMocks private PersonFlows flow;

    @Before public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test public void test() throws IOException {
        Account acc = new Account();
        acc.setCurrencyId(FROM_CUR_ID);
        acc.setId(ACC_ID);
        acc.setAmount(AMMOUNT);

        when(currencyService.get(FROM_CUR_ID)).thenReturn(createCurrency("br", FROM_CUR_ID, FROM_CUR_MULTY));
        when(currencyService.get(TO_CUR_ID)).thenReturn(createCurrency("us", TO_CUR_ID, TO_CUR_MULTY));

        flow.changeAccountToCurrency(acc, TO_CUR_ID);

        verify(accountService).saveOrUpdate(acc);
        System.out.println(acc.getAmount().intValue());

        assertEquals(acc.getCurrencyId().intValue(), TO_CUR_ID);
        assertEquals(acc.getAmount().intValue(), 1000);
    }

    private Currency createCurrency(String name, Integer id, Double multy) {
        Currency currency = new Currency();
        currency.setName(name);
        currency.setId(id);
        currency.setMultiplier(multy);
        return currency;
    }

}
