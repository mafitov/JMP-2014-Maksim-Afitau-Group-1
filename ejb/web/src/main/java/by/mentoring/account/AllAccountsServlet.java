package by.mentoring.account;

import by.mentoring.ejb.AccountServiceLocal;
import by.mentoring.ejb.PersonServiceLocal;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(urlPatterns = {"/accounts"})
public class AllAccountsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    @EJB
    AccountServiceLocal accountServiceLocal;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("accounts", accountServiceLocal.getAll());
        request.getRequestDispatcher("/WEB-INF/jsps/accounts/accounts.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        doGet(httpServletRequest, httpServletResponse);
    }
}