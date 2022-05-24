package org.celebration.celebrationorganization.dashboard.celebration.controller;

import jakarta.inject.Inject;
import jakarta.persistence.NoResultException;
import jakarta.persistence.NonUniqueResultException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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
import java.util.logging.Logger;

@WebServlet(name = "UpdateCelebrationServlet", value = "/updateCelebration")
public class UpdateCelebrationServlet extends HttpServlet {

    @Inject
    private CelebrationServiceLocal celebrationService;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException {
        Integer celebrationId = Integer.parseInt(request.getParameter("celebrationId"));

        try {
            Celebration celebration = celebrationService.find(celebrationId);
            if (celebration != null) {
                String celebrationDate = request.getParameter("celebrationDate");
                SimpleDateFormat format = new SimpleDateFormat("YYYY-MM-DD");
                Date date = format.parse(celebrationDate);
                User user = UserSession.USER.getFromSession(request);

                celebration.setCelebrationDate(date);
                celebration.setName(request.getParameter("celebrationName"));
                celebration.setUserCreator(user);
                celebrationService.edit(celebration);

                List<Celebration> celebrations = celebrationService.findAll();
                request.setAttribute("celebrations", celebrations);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher(Routes.CELEBRATION);
                requestDispatcher.include(request, response);
            }
        } catch (ParseException | NoResultException | NonUniqueResultException e) {
            Logger.getLogger(getClass().getName()).info(e.getMessage());
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
