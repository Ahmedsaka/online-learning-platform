package io.medalytics.onlinelearningplatform.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserSignUpRequest {
    private String firstName;
    private String lastName;
    private String username;
    private Set<String> roleType;
    private String email;
    private String password;
}
