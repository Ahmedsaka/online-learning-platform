package io.medalytics.onlinelearningplatform.controller;

import io.medalytics.onlinelearningplatform.model.User;
import io.medalytics.onlinelearningplatform.model.UserSignUpRequest;
import io.medalytics.onlinelearningplatform.service.CustomUserServiceDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(path = "/api/v1/authenticate")
public class AdminUserController {

    private CustomUserServiceDetails userServiceDetails;

    @Autowired
    public AdminUserController(CustomUserServiceDetails userServiceDetails) {
        this.userServiceDetails = userServiceDetails;
    }

    @PostMapping(path = "/signUp", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("permitAll()")
    public ResponseEntity<User> signUp(@RequestBody UserSignUpRequest request){
        return ResponseEntity.ok(
                userServiceDetails.save(
                        User.builder()
                                .firstName(request.getFirstName())
                                .lastName(request.getLastName())
                                .username(request.getUsername())
                                .email(request.getEmail())
                                .password(request.getPassword())
//                                .roles(request.getRoleType().add(""))
                                .build()
                )
        );
    }
}
