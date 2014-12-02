package by.mentoring.account;

import by.mentoring.ejb.AccountServiceLocal;
import by.mentoring.ejb.CurrencyServiceLocal;
import by.mentoring.model.Account;
import by.mentoring.model.Person;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;

@WebServlet(urlPatterns = {"/accounts/new"})
public class AddAccountServlet extends HttpServlet {

    @EJB
    CurrencyServiceLocal currencyServiceLocal;

    @EJB
    AccountServiceLocal accountServiceLocal;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("currencies", currencyServiceLocal.getAll());
        request.getRequestDispatcher("/WEB-INF/jsps/accounts/new-account.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer currencyId = Integer.parseInt(request.getParameter("currencyId"));
        Double amount = Double.valueOf(request.getParameter("amount"));
        Account acount = new Account();
        acount.setCurrencyId(currencyId);
        acount.setAmount(BigDecimal.valueOf(amount));

        accountServiceLocal.saveOrUpdate(acount);

        request.getRequestDispatcher("/accounts").forward(request, response);
    }
}
