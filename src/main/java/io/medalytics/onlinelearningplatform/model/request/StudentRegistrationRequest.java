package io.medalytics.onlinelearningplatform.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StudentRegistrationRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
