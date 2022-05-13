package org.celebration.celebrationorganization.ejb.service;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.NonUniqueResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.celebration.celebrationorganization.entity.User;

@Stateless
public class UserService extends AbstractService<User> implements UserServiceLocal{

    @PersistenceContext(unitName = "birthdayPU")
    private EntityManager entityManager;

    public UserService(){
        super(User.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }
}
