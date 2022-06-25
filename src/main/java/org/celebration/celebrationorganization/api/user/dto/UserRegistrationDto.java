package org.celebration.celebrationorganization.api.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
@Getter
@Setter
@NoArgsConstructor
public class UserRegistrationDto implements Serializable {
    private String name;
    private String surname;
    private String username;
    private String plainPassword;
    private String contact;
    private String email;
    private String town;
}
