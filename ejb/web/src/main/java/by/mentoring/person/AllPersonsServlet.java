package by.mentoring.person;

import by.mentoring.ejb.PersonServiceLocal;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(urlPatterns = {"/persons"})
public class AllPersonsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    @EJB
    PersonServiceLocal personServiceBean;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("persons", personServiceBean.getAll());
        request.getRequestDispatcher("/WEB-INF/jsps/person/persons.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        doGet(httpServletRequest, httpServletResponse);
    }
}
