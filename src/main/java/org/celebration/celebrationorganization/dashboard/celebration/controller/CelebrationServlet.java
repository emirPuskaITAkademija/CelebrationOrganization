package org.celebration.celebrationorganization.dashboard.celebration.controller;

import jakarta.inject.Inject;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.celebration.celebrationorganization.ejb.celebration.entity.Celebration;
import org.celebration.celebrationorganization.ejb.celebration.service.CelebrationServiceLocal;
import org.celebration.celebrationorganization.routes.Routes;
import org.celebration.celebrationorganization.user.session.UserSession;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "CelebrationServlet", value = "/celebration")
public class CelebrationServlet extends HttpServlet {

    @Inject
    private CelebrationServiceLocal celebrationServiceLocal;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if(UserSession.USER.getFromSession(request)!=null){
            celebrationServiceLocal.invalidateCache();
            List<Celebration> celebrations = celebrationServiceLocal.findAll();
            request.setAttribute("celebrations", celebrations);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(Routes.CELEBRATION);
            requestDispatcher.include(request, response);
        }else{
            //REDIRECT kao na DashboardAccessServlet
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
