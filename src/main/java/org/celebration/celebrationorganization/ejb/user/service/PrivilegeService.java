package org.celebration.celebrationorganization.ejb.user.service;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.celebration.celebrationorganization.ejb.service.AbstractService;
import org.celebration.celebrationorganization.ejb.user.entity.Privilege;

@Stateless
public class PrivilegeService extends AbstractService<Privilege> implements PrivilegeServiceLocal {
    @PersistenceContext(name = "birthdayPU")
    private EntityManager entityManager;

    public PrivilegeService() {
        super(Privilege.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }
}