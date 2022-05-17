package org.celebration.celebrationorganization.ejb.user.service;

import jakarta.ejb.Local;
import org.celebration.celebrationorganization.ejb.user.entity.Privilege;

import java.util.List;

@Local
public interface PrivilegeServiceLocal {
    void create(Privilege privilege);

    void edit(Privilege privilege);

    void remove(Privilege privilege);

    Privilege find(Object id);

    List<Privilege> findAll();
}