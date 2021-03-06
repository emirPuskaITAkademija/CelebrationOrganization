package org.celebration.celebrationorganization.user.authentication.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.celebration.celebrationorganization.routes.Routes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * <li>1. browser http://localhost:8080/CelebrationOrganization-1.0-SNAPSHOT/
 *                 http://localhost:8080/CelebrationOrganization-1.0-SNAPSHOT/login
 *                 HTPP GET request</li>
 */
@WebServlet(name = "AuthenticationDispatcherServlet", value = "/login")
public class LoginDispatcherServlet extends HttpServlet {


    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(Routes.AUTHENTICATION);
        requestDispatcher.forward(request, response);
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
