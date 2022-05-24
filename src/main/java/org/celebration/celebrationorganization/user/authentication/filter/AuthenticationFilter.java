package org.celebration.celebrationorganization.user.authentication.filter;


import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.celebration.celebrationorganization.ejb.user.entity.User;
import org.celebration.celebrationorganization.user.session.UserSession;

import java.io.IOException;
//view UI -> servlet  -> /dashboard/
//dashboardAccess
//login
//authenticate

//http://localhost:8080/CelebrationOrganization-1.0-SNAPSHOT/dashboard/dashboard.jsp
//http://localhost:8080/CelebrationOrganization-1.0-SNAPSHOT/dashboardAccess
@WebFilter(urlPatterns = {"/dashboard/*"})
public class AuthenticationFilter implements Filter {

    private ServletContext context;//application

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.context = filterConfig.getServletContext();
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse res = (HttpServletResponse) response;
        HttpServletRequest req = (HttpServletRequest) request;
        User sessionUser = UserSession.USER.getFromSession(req);
        if(sessionUser ==null){
            res.sendRedirect(req.getContextPath() + "/login");
        }else{
            chain.doFilter(request, response);
        }
    }
}
