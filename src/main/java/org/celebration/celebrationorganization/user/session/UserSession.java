package org.celebration.celebrationorganization.user.session;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.celebration.celebrationorganization.ejb.user.entity.User;

public enum UserSession {
    USER("SESSION_KEY");

    private String sessionKey;

    private UserSession(String sessionKey){
        this.sessionKey = sessionKey;
    }

    public void addToSession(User user, HttpServletRequest request){
        HttpSession session = request.getSession();
        if(session.getAttribute(sessionKey) == null){
            session.setAttribute(sessionKey, user);
        }
    }

    public User getFromSession(HttpServletRequest request){
        HttpSession session = request.getSession();
        return (User) session.getAttribute(sessionKey);
    }
}
