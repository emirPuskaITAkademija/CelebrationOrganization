package org.celebration.celebrationorganization.api.user;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.celebration.celebrationorganization.api.user.dto.UserDto;
import org.celebration.celebrationorganization.api.user.dto.UserRegistrationDto;
import org.celebration.celebrationorganization.ejb.user.entity.User;
import org.celebration.celebrationorganization.ejb.user.service.UserServiceLocal;
import org.celebration.celebrationorganization.user.registration.model.RegistrationModel;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <li>
 * 1. http://localhost:8080/CelebrationOrganization-1.0-SNAPSHOT
 * </li>
 * <li>
 * 2. /api -> CelebrationRestConfig
 * </li>
 * <li>
 * 3. /users
 * </li>
 * <p>
 * FULL URL : <li> http://localhost:8080/CelebrationOrganization-1.0-SNAPSHOT/api/users </li>
 */
@Path("/users")
public class UserRestService {

    @Inject
    private UserServiceLocal userServiceLocal;

    /**
     * SELECT all users when comes HTTP request with GET method.
     * <p>
     * on URL http://localhost:8080/CelebrationOrganization-1.0-SNAPSHOT/api/users
     * </p>
     *
     * @return userDtos
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<UserDto> getUsers() {
//        List<User> users = ;
//        List<UserDto> userDtos = new ArrayList<>();
//        for(User user : users){
//            UserDto userDto = mapToUserDto(user);
//            userDtos.add(userDto);
//        }
        return userServiceLocal
                .findAll()//List<User>
                .stream()//Stream<User>
                .map(UserDto::mapToUserDto)//Stream<UserDto>
                .collect(Collectors.toList());
    }

    /**
     * SELECT one ser when comes HTTP request with GET method.
     * <p>
     * on URL http://localhost:8080/CelebrationOrganization-1.0-SNAPSHOT/api/users/id_value
     * </p>
     *
     * @return userDtos
     */
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public UserDto getUser(@PathParam("id") int id) {
        User user = userServiceLocal.find(id);
        return UserDto.mapToUserDto(user);
    }

    /**
     * Ova metoda prima application/json ili application/xml tip sadržaja.
     * Ukoliko takav tip sadržaja dođe u HTTP request BODY-u ona će to zahvaljujući Jersey-u
     * dobiti konvertovano u UserRegistrationDto objekat.
     * <p>
     *     Ova metoda je dostupna na:
     *     <li>URL: http://localhost:8080/CelebrationOrganization-1.0-SNAPSHOT/api/users</li>
     *     <li>mapirana je na HTTP POST zahtjev</li>
     * </p>
     *
     * @param userRegistrationDto
     * @return message
     */
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response createUser(UserRegistrationDto userRegistrationDto) {
        RegistrationModel registrationModel = RegistrationModel.builder()
                .username(userRegistrationDto.getUsername())
                .plainPassword(userRegistrationDto.getPlainPassword())
                .name(userRegistrationDto.getName())
                .surname(userRegistrationDto.getSurname())
                .contact(userRegistrationDto.getContact())
                .email(userRegistrationDto.getEmail())
                .town(userRegistrationDto.getTown())
                .build();
        User createdUser = userServiceLocal.register(registrationModel);
        return Response
                .created(URI.create("http://localhost:8080/CelebrationOrganization-1.0-SNAPSHOT/api/users/"+createdUser.getId()))
                .build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response updateUser(@PathParam("id") int id, UserRegistrationDto userRegistrationDto){
        User user = userServiceLocal.find(id);
        if(user != null){
            user.setName(userRegistrationDto.getName());
            user.setSurname(userRegistrationDto.getSurname());
            user.setContact(userRegistrationDto.getContact());
            user.setEmail(userRegistrationDto.getEmail());
            userServiceLocal.edit(user);
            return Response.ok(user).build();
        }else{
            return Response.notModified().build();
        }
    }

    @DELETE
    @Path("{id}")
    public Response deleteUser(@PathParam("id") int id){
        User user = userServiceLocal.find(id);
        if(user != null){
            userServiceLocal.remove(user);
            //200 OK
            return Response.ok().build();
        }else{
            //304 Not Modified
            return Response.notModified().build();
        }
    }
}
