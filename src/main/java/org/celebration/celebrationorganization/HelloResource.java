package org.celebration.celebrationorganization;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.celebration.celebrationorganization.ejb.user.entity.User;
import org.celebration.celebrationorganization.ejb.user.service.UserServiceLocal;
import org.celebration.celebrationorganization.api.user.dto.UserDto;
import org.celebration.celebrationorganization.api.user.dto.UserRegistrationDto;
import org.celebration.celebrationorganization.user.registration.model.RegistrationModel;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Path -> veza URL-a specifičnog API ili web servisa koja handla response
 * GET -> BAZNI_URL/api/hello
 */
@Path("/hello")
public class HelloResource {
    @Inject
    private UserServiceLocal userServiceLocal;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String sayHello() {
        return "Vozdra raja";
    }

    @GET
    @Path("/users")
    @Produces(MediaType.APPLICATION_JSON)
    public List<UserDto> getUsers() {
        return userServiceLocal.findAll()
                .stream()//
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @POST
    @Path("/add/user")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public String addUser(UserRegistrationDto userRegistrationDto){
        RegistrationModel registrationModel = RegistrationModel.builder()
                .name(userRegistrationDto.getName())
                .surname(userRegistrationDto.getSurname())
                .username(userRegistrationDto.getUsername())
                .plainPassword(userRegistrationDto.getPlainPassword())
                .email(userRegistrationDto.getEmail())
                .town(userRegistrationDto.getTown())
                .contact(userRegistrationDto.getContact())
                .build();
       User user =  userServiceLocal.register(registrationModel);
       return "Kreiran korisnik: " + user.getName();
    }

    private UserDto mapToDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setUsername(user.getUsername());
        userDto.setName(user.getName());
        userDto.setSurname(user.getSurname());
        return userDto;
    }
}
