package org.celebration.celebrationorganization.api.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.celebration.celebrationorganization.ejb.user.entity.User;

import java.io.Serializable;
@Getter
@Setter
@NoArgsConstructor
public class UserDto implements Serializable {
    private String username;
    private String name;
    private String surname;
    private String email;
    private String contact;
    private String town;
    private String privilege;

    public static UserDto mapToUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setName(user.getName());
        userDto.setSurname(user.getSurname());
        userDto.setUsername(user.getUsername());
        userDto.setTown(user.getTown().getName());
        userDto.setEmail(user.getEmail());
        userDto.setContact(user.getContact());
        userDto.setPrivilege(user.getPrivilege().getName());
        return userDto;
    }
}
