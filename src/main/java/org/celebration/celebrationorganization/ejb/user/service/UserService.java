package org.celebration.celebrationorganization.ejb.user.service;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.NonUniqueResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.security.enterprise.identitystore.Pbkdf2PasswordHash;
import org.celebration.celebrationorganization.ejb.service.AbstractService;
import org.celebration.celebrationorganization.ejb.town.entity.Town;
import org.celebration.celebrationorganization.ejb.town.service.TownServiceLocal;
import org.celebration.celebrationorganization.ejb.user.entity.Privilege;
import org.celebration.celebrationorganization.ejb.user.entity.User;
import org.celebration.celebrationorganization.user.authentication.model.AuthenticationModel;
import org.celebration.celebrationorganization.user.registration.model.RegistrationModel;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * EJB Enterprise Jakarta Bean
 * Jakarta 9.1.0
 * <p>
 *     Dvije vrste:
 *     1. Local Stateless SessionBean
 * </p>
 */
@Stateless
public class UserService extends AbstractService<User> implements UserServiceLocal{

    @PersistenceContext(unitName = "birthdayPU")
    private EntityManager entityManager;

    @Inject
    private Pbkdf2PasswordHash pbkdf2PasswordHash;

    @Inject
    private TownServiceLocal townServiceLocal;

    @Inject
    private PrivilegeServiceLocal privilegeServiceLocal;

    public UserService(){
        super(User.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }

    //nedim.ziga  1. unio dobar password
    //nedim.ziga  2. unio loš password
    //neila.hasanovic
    @Override
    public User login(AuthenticationModel authenticationModel) {
        User user = null;
        try{
            user = findByUsername(authenticationModel.getUsername());
            if(user == null){
                return null;
            }
            String plainPassword = authenticationModel.getPassword();
            String hashedPassword = user.getPassword();
            if (!pbkdf2PasswordHash.verify(plainPassword.toCharArray(), hashedPassword)) {
                user = null;
                throw new NoResultException("Wrong password");
            }
        }catch (NonUniqueResultException | NoResultException e){
            Logger.getLogger("USERNAME QUERY").log(Level.INFO, e.getMessage());
        }
        return user;
    }

    @Override
    public User findByUsername(String username) {
        User user = null;
        try{
            Query query = entityManager.createNamedQuery("User.findByUsername");
            query.setParameter("username", username);
            user = (User) query.getSingleResult();
            return user;
        }catch (NonUniqueResultException | NoResultException e){
            Logger.getLogger("USERNAME QUERY").log(Level.INFO, e.getMessage());
        }
        return user;
    }

    @Override
    public User register(RegistrationModel registerModel) {
        User user = findByUsername(registerModel.getUsername());
        if (user == null) {
            user = new User();
            user.setName(registerModel.getName());
            user.setSurname(registerModel.getSurname());
            user.setUsername(registerModel.getUsername());
            String hashedPassword = pbkdf2PasswordHash.generate(registerModel.getPlainPassword().toCharArray());
            user.setPassword(hashedPassword);
            Town selectedTown = townServiceLocal.find(registerModel.getTownId());
            user.setTown(selectedTown);
            user.setEmail(registerModel.getEmail());
            user.setContact(registerModel.getContact());
            Privilege privilege = privilegeServiceLocal.find(Privilege.CLIENT_PRIVILEGE);
            user.setPrivilege(privilege);
            user.setStatus("active");
            create(user);
        }
        return user;
    }
}
