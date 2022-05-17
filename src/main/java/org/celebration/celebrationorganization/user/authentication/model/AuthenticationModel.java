package org.celebration.celebrationorganization.user.authentication.model;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Data
@Builder
public class AuthenticationModel {
    private String username;
    private String password;
}
