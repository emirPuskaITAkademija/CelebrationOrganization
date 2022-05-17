package org.celebration.celebrationorganization;

import jakarta.inject.Inject;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.celebration.celebrationorganization.ejb.user.service.UserServiceLocal;
import org.celebration.celebrationorganization.ejb.user.entity.User;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

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