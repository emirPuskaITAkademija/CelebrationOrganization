package org.celebration.celebrationorganization.dashboard.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.celebration.celebrationorganization.routes.Routes;
import org.celebration.celebrationorganization.user.session.UserSession;

import java.io.IOException;

@WebServlet(name = "DashboardAccessServlet", urlPatterns = {"/dashboardAccess"})
public class DashboardAccessServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(UserSession.USER.getFromSession(req)!=null){
            RequestDispatcher requestDispatcher = req.getRequestDispatcher(Routes.DASHBOARD);
            requestDispatcher.forward(req, resp);
        }else{
            resp.sendRedirect(req.getContextPath());
//            RequestDispatcher requestDispatcher = req.getRequestDispatcher(Routes.LOGIN);
//            requestDispatcher.forward(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }
}
