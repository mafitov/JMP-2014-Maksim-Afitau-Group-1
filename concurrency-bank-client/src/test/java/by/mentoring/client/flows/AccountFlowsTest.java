package by.mentoring.client.flows;

import by.mentoring.model.Account;
import by.mentoring.model.Currency;
import by.mentoring.service.AccountService;
import by.mentoring.service.CurrencyServie;
import com.google.common.collect.Lists;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.argThat;
import static org.hamcrest.Matchers.isOneOf;
import static org.mockito.Mockito.*;

public class AccountFlowsTest {

    private static final int CURRENCY_ID = 1;

    @Mock
    private AccountService accountService;
    @Mock
    private CurrencyServie currencyService;

    @InjectMocks
    private AccountFlows flow;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testShowAllAccounts() throws IOException {

        Account account1 = new Account();
        account1.setCurrencyId(CURRENCY_ID);
        Account account2 = new Account();
        account2.setCurrencyId(CURRENCY_ID);

        List<Account> accounts = Lists.newArrayList(account1, account2);

        when(accountService.getAll()).thenReturn(accounts);
        when(currencyService.get(anyInt())).thenReturn(new Currency());

        flow.showAllAccounts();

        verify(currencyService, times(2)).get(CURRENCY_ID);
    }
}
