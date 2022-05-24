package org.celebration.celebrationorganization.user.authentication.controller;

import jakarta.inject.Inject;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.celebration.celebrationorganization.ejb.user.service.UserServiceLocal;
import org.celebration.celebrationorganization.ejb.user.entity.User;
import org.celebration.celebrationorganization.routes.Routes;
import org.celebration.celebrationorganization.user.authentication.model.AuthenticationModel;
import org.celebration.celebrationorganization.user.session.UserSession;

import java.io.IOException;

@WebServlet(name = "AuthenticateDispatcherServlet", value = "/authenticate")
public class AuthenticateDispatcherServlet extends HttpServlet {

    @Inject
    private UserServiceLocal userServiceLocal;

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AuthenticationModel authenticationModel = AuthenticationModel.builder()
                .username(request.getParameter("username"))
                .password(request.getParameter("password"))
                .build();
        //user -> username
        User user = userServiceLocal.login(authenticationModel);
        if(user != null){
            UserSession.USER.addToSession(user, request);
            RequestDispatcher dashBoardDispatcher = request.getRequestDispatcher(Routes.DASHBOARD_ACCESS);
            dashBoardDispatcher.forward(request, response);
        }else{
            RequestDispatcher loginDispatcher = request.getRequestDispatcher(Routes.AUTHENTICATION);
            loginDispatcher.forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}
