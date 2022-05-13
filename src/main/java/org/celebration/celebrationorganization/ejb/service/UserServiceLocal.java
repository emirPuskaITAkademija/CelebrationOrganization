package org.celebration.celebrationorganization.ejb.service;

import jakarta.ejb.Local;
import org.celebration.celebrationorganization.entity.User;

import java.util.List;

//lokalno session bean
@Local
public interface UserServiceLocal {

    void create(User user);

    void edit(User user);

    void remove(User user);

    User find(Object id);

    List<User> findAll();
}
