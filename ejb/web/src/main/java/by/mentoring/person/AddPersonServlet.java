package by.mentoring.person;

import by.mentoring.ejb.PersonServiceLocal;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.Override;
import by.mentoring.model.Person;

@WebServlet(urlPatterns = {"/persons/new"})
public class AddPersonServlet extends HttpServlet {
    @EJB
    PersonServiceLocal personServiceBean;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/jsps/person/new-person.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        Person person = new Person();
        person.setName(name);

        personServiceBean.saveOrUpdate(person);

        request.getRequestDispatcher("/persons").forward(request, response);
    }
}
