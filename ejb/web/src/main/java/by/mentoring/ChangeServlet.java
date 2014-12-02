package by.mentoring;

import by.mentoring.ejb.*;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;

@WebServlet(urlPatterns = {"/bank"})
public class ChangeServlet extends HttpServlet {
    @EJB
    PersonServiceLocal personServiceBean;
    @EJB
    CurrencyServiceLocal currencyServiceLocal;
    @EJB
    AccountServiceLocal accountServiceLocal;
    @EJB
    MoneyChangeLocal moneyChanger;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("persons", personServiceBean.getAll());
        request.setAttribute("accounts", accountServiceLocal.getAll());
        request.getRequestDispatcher("/WEB-INF/jsps/change.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer from = Integer.parseInt(request.getParameter("from"));
        Integer to = Integer.parseInt(request.getParameter("to"));
        BigDecimal amount = BigDecimal.valueOf(Double.valueOf(request.getParameter("amount")));
        moneyChanger.change(from, to, amount);
        request.getRequestDispatcher("/accounts").forward(request, response);
    }
}
