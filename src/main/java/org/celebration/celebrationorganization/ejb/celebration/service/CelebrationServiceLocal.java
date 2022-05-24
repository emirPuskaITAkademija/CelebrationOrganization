package org.celebration.celebrationorganization.ejb.celebration.service;

import jakarta.ejb.Local;
import org.celebration.celebrationorganization.ejb.celebration.entity.Celebration;

import java.util.List;

@Local
public interface CelebrationServiceLocal {
    void create(Celebration celebration);

    void edit(Celebration celebration);

    void remove(Celebration celebration);

    Celebration find(Object id);

    List<Celebration> findAll();


    void invalidateCache();
}
