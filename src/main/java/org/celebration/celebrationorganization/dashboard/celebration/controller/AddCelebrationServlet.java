package org.celebration.celebrationorganization.dashboard.celebration.controller;

import jakarta.inject.Inject;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import lombok.SneakyThrows;
import org.celebration.celebrationorganization.ejb.celebration.entity.Celebration;
import org.celebration.celebrationorganization.ejb.celebration.service.CelebrationServiceLocal;
import org.celebration.celebrationorganization.ejb.user.entity.User;
import org.celebration.celebrationorganization.routes.Routes;
import org.celebration.celebrationorganization.user.session.UserSession;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet(name = "AddCelebrationServlet", value = "/addCelebration")
public class AddCelebrationServlet extends HttpServlet {

    @Inject
    private CelebrationServiceLocal celebrationService;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException {
        User sessionUser = UserSession.USER.getFromSession(request);
        if(sessionUser != null){
            //ekstraktujem iz requesta ono što će korisnik popuniti u formi
            String celebrationName = request.getParameter("celebrationName");
            String celebrationDate = request.getParameter("celebrationDate");
            SimpleDateFormat format = new SimpleDateFormat("YYYY-MM-DD");
            Date date = format.parse(celebrationDate);
            User user = UserSession.USER.getFromSession(request);
            //kreiranje proslave odnosno zahtjeva
            Celebration celebration = new Celebration();
            celebration.setName(celebrationName);
            celebration.setCelebrationDate(date);
            celebration.setUserCreator(sessionUser);
            celebrationService.create(celebration);
            List<Celebration> celebrations = celebrationService.findAll();
            request.setAttribute("celebrations", celebrations);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(Routes.CELEBRATION);
            requestDispatcher.include(request, response);
        }else {
            //REDIRECT na login
        }

    }
    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}
