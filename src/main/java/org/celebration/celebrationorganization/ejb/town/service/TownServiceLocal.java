package org.celebration.celebrationorganization.ejb.town.service;


import jakarta.ejb.Local;
import org.celebration.celebrationorganization.ejb.town.entity.Town;

import java.util.List;

@Local
public interface TownServiceLocal {

    void create(Town town);

    void edit(Town town);

    void remove(Town town);

    Town find(Object id);

    List<Town> findAll();
}
