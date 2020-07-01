package io.medalytics.onlinelearningplatform.model.request;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UsernamePasswordAuthenticationRequest {
    private String username;
    private String password;
}
