package io.medalytics.onlinelearningplatform.controller;

import io.medalytics.onlinelearningplatform.model.Role;
import io.medalytics.onlinelearningplatform.model.User;
import io.medalytics.onlinelearningplatform.model.UserSignUpRequest;
import io.medalytics.onlinelearningplatform.service.CustomUserServiceDetails;
import io.medalytics.onlinelearningplatform.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;


@RestController
@RequestMapping(path = "/api/v1/authenticate")
public class AdminUserController {

    private CustomUserServiceDetails userServiceDetails;
    private RoleService roleService;

    @Autowired
    public AdminUserController(CustomUserServiceDetails userServiceDetails, RoleService roleService) {
        this.userServiceDetails = userServiceDetails;
        this.roleService = roleService;
    }

    @PostMapping(path = "/signUp", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("permitAll()")
    public ResponseEntity<User> signUp(@RequestBody UserSignUpRequest request){

        userServiceDetails.userExist(request.getUsername());

        Set<Role> roles = new HashSet<>();
        roles.add(
                roleService.findByName(request.getRoleType())
        );
        return ResponseEntity.ok(
                userServiceDetails.save(
                        User.builder()
                                .firstName(request.getFirstName())
                                .lastName(request.getLastName())
                                .username(request.getUsername())
                                .email(request.getEmail())
                                .password(request.getPassword())
                                .roles(roles)
                                .build()
                )
        );
    }
}
