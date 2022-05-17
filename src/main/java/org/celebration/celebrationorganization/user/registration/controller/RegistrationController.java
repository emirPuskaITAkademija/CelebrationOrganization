package org.celebration.celebrationorganization.user.registration.controller;

import lombok.AllArgsConstructor;
import org.celebration.celebrationorganization.ejb.user.entity.User;
import org.celebration.celebrationorganization.ejb.user.service.UserServiceLocal;
import org.celebration.celebrationorganization.user.registration.model.RegistrationModel;
@AllArgsConstructor
public class RegistrationController {
    private final UserServiceLocal userServiceLocal;
    private final RegistrationModel registrationModel;

    public boolean usernameOccupied() {
        User user = userServiceLocal.findByUsername(registrationModel.getUsername());
        return user != null;
    }

    public boolean isValidRegistrationModel() {
        return !registrationModel.getName().isEmpty()
                && !registrationModel.getSurname().isEmpty()
                && !registrationModel.getUsername().isEmpty()
                && !registrationModel.getPlainPassword().isEmpty()
                && !registrationModel.getEmail().isEmpty()
                && !registrationModel.getTown().isEmpty()
                && !registrationModel.getContact().isEmpty();
    }
}
