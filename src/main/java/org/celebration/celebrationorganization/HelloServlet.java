package org.celebration.celebrationorganization;

import java.io.*;
import java.util.List;

import jakarta.inject.Inject;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.celebration.celebrationorganization.ejb.service.UserService;
import org.celebration.celebrationorganization.ejb.service.UserServiceLocal;
import org.celebration.celebrationorganization.entity.User;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {

    @Inject
    private UserServiceLocal userServiceLocal;

    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        List<User> users = userServiceLocal.findAll();
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        out.println("<ul>");
        for(User user: users){
            out.println("<li>"+user.getName()+" "+user.getSurname()+"</li>");
        }
        out.println("</ul>");
        out.println("</body></html>");
    }

    public void destroy() {
    }
}