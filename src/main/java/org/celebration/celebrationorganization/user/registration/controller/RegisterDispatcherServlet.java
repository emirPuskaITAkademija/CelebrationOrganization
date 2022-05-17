package org.celebration.celebrationorganization.user.registration.controller;

import jakarta.inject.Inject;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.celebration.celebrationorganization.ejb.town.entity.Town;
import org.celebration.celebrationorganization.ejb.town.service.TownServiceLocal;
import org.celebration.celebrationorganization.ejb.user.entity.User;
import org.celebration.celebrationorganization.ejb.user.service.UserServiceLocal;
import org.celebration.celebrationorganization.routes.Routes;
import org.celebration.celebrationorganization.user.registration.model.RegistrationModel;

import java.io.IOException;
import java.util.List;
@WebServlet(name = "RegisterDispatcherServlet", urlPatterns = {"/register"})
public class RegisterDispatcherServlet extends HttpServlet {

    @Inject
    private TownServiceLocal townServiceLocal;

    @Inject
    private UserServiceLocal userServiceLocal;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RegistrationModel registerModel = RegistrationModel.builder()
                .name(request.getParameter("name"))
                .surname(request.getParameter("surname"))
                .username(request.getParameter("username"))
                .plainPassword(request.getParameter("password"))
                .contact(request.getParameter("contact"))
                .email(request.getParameter("email"))
                .town(request.getParameter("town"))
                .build();
        RegistrationController controller = new RegistrationController(userServiceLocal, registerModel);
        if(controller.isValidRegistrationModel()){
            if(controller.usernameOccupied()){

            }else{
                User user = userServiceLocal.register(registerModel);
                if(user !=null){
                    RequestDispatcher loginDispatcher = request.getRequestDispatcher(Routes.AUTHENTICATION);
                    loginDispatcher.forward(request, response);
                }else{

                }
            }
        }else{
            RequestDispatcher registerDispatcher = request.getRequestDispatcher(Routes.REGISTRATION);
            registerDispatcher.forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
