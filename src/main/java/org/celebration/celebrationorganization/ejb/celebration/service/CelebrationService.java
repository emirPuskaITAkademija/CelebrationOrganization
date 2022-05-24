package org.celebration.celebrationorganization.ejb.celebration.service;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.celebration.celebrationorganization.ejb.celebration.entity.Celebration;
import org.celebration.celebrationorganization.ejb.service.AbstractService;

import java.util.List;

@Stateless
public class CelebrationService extends AbstractService<Celebration>
        implements CelebrationServiceLocal{


    @PersistenceContext(unitName = "birthdayPU")
    private EntityManager entityManager;

    public CelebrationService() {
        super(Celebration.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }


    @Override
    public void invalidateCache() {
        entityManager.getEntityManagerFactory().getCache().evictAll();
    }
}
