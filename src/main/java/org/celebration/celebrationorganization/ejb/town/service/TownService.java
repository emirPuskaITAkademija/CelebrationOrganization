package org.celebration.celebrationorganization.ejb.town.service;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.celebration.celebrationorganization.ejb.service.AbstractService;
import org.celebration.celebrationorganization.ejb.town.entity.Town;

@Stateless
public class TownService extends AbstractService<Town> implements TownServiceLocal {

    @PersistenceContext(unitName = "birthdayPU")
    private EntityManager entityManager;

    public TownService() {
        super(Town.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }

}
