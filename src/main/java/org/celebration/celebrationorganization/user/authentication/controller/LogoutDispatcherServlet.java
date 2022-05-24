package org.celebration.celebrationorganization.user.authentication.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.celebration.celebrationorganization.routes.Routes;
import org.celebration.celebrationorganization.user.session.UserSession;

import java.io.IOException;

@WebServlet(name = "LogoutDispatcherServlet", urlPatterns = {"/logout"})
public class LogoutDispatcherServlet extends HttpServlet {
    protected void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //čišćenje sesije
        HttpSession session = req.getSession();
        resp.setHeader("Cache-Control", "no-cache, no-store");
        resp.setHeader("Pragma", "no-cache");
        session.invalidate();
        //redirect na login  localhost:8080/login
        resp.sendRedirect(req.getContextPath());
       /* RequestDispatcher requestDispatcher = req.getRequestDispatcher(Routes.LOGIN);
        //forward
        requestDispatcher.forward(req, resp);*/
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
