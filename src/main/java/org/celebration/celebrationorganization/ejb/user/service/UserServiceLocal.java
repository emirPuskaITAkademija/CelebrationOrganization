package org.celebration.celebrationorganization.ejb.user.service;

import jakarta.ejb.Local;
import org.celebration.celebrationorganization.ejb.user.entity.User;
import org.celebration.celebrationorganization.user.authentication.model.AuthenticationModel;
import org.celebration.celebrationorganization.user.registration.model.RegistrationModel;

import java.util.List;

//lokalno session bean
@Local
public interface UserServiceLocal {

    void create(User user);

    void edit(User user);

    void remove(User user);

    User find(Object id);

    List<User> findAll();

    User login(AuthenticationModel authenticationModel);

    User findByUsername(String username);

    User register(RegistrationModel registrationModel);
}
