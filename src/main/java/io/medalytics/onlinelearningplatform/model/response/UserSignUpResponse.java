package io.medalytics.onlinelearningplatform.model.response;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserSignUpResponse {
    private String firstName;
    private String lastName;
    private String username;
    private String email;
}
