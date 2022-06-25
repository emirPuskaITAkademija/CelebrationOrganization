package org.celebration.celebrationorganization.ejb.service;

import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

/**
 * CRUD
 * Create
 * Retrieve
 * Update
 * Delete
 *
 * @param <E>
 */
public abstract class AbstractService<E>{

    private Class<E> entityClass;

    public AbstractService(Class<E> entityClass){
        this.entityClass = entityClass;
    }

    protected abstract EntityManager getEntityManager();

    public void create(E entity){
        getEntityManager().persist(entity);
    }

    public void edit(E entity){
        getEntityManager().merge(entity);
    }

    public void remove(E entity){
        //Ako je entity u otkačenom stanju
        if(!getEntityManager().contains(entity)){
            entity = getEntityManager().merge(entity);
        }
        getEntityManager().remove(entity);
    }

    public E find(Object id){
        return getEntityManager().find(entityClass, id);
    }

    public List<E> findAll(){
        CriteriaQuery criteriaQuery = getEntityManager().getCriteriaBuilder().createQuery();
        criteriaQuery.select(criteriaQuery.from(entityClass));
        return getEntityManager().createQuery(criteriaQuery).getResultList();
    }
}
