package org.celebration.celebrationorganization.user.registration.controller;

import jakarta.inject.Inject;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.celebration.celebrationorganization.ejb.town.entity.Town;
import org.celebration.celebrationorganization.ejb.town.service.TownServiceLocal;
import org.celebration.celebrationorganization.routes.Routes;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "RegistrationDispatcherServlet", value = "/registration")
public class RegistrationDispatcherServlet extends HttpServlet {

    @Inject
    private TownServiceLocal townServiceLocal;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Town> towns = townServiceLocal.findAll();
        request.setAttribute("towns", towns);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(Routes.REGISTRATION);
        requestDispatcher.forward(request, response);
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
