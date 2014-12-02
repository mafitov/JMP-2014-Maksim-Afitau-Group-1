package by.mentoring.person;


import by.mentoring.ejb.PersonServiceLocal;
import by.mentoring.model.Person;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/persons/id"})
public class EditPersonServlet extends HttpServlet {
    @EJB
    PersonServiceLocal personServiceBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        request.setAttribute("person", personServiceBean.get(id));
        request.getRequestDispatcher("/WEB-INF/jsps/person/edit-person.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        Person person = personServiceBean.get(id);
        person.setName(name);

        personServiceBean.saveOrUpdate(person);

        request.getRequestDispatcher("/persons").forward(request, response);
    }
}
