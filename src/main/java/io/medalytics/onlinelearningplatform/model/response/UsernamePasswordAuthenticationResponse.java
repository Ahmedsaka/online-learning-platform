package io.medalytics.onlinelearningplatform.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter@Setter
public class UsernamePasswordAuthenticationResponse {

    private String jwtToken;
}
