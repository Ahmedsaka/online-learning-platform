package io.medalytics.onlinelearningplatform.controller;

import io.medalytics.onlinelearningplatform.model.Role;
import io.medalytics.onlinelearningplatform.model.User;
import io.medalytics.onlinelearningplatform.model.UserSignUpRequest;
import io.medalytics.onlinelearningplatform.model.request.UsernamePasswordAuthenticationRequest;
import io.medalytics.onlinelearningplatform.model.response.UsernamePasswordAuthenticationResponse;
import io.medalytics.onlinelearningplatform.service.CustomUserDetailsService;
import io.medalytics.onlinelearningplatform.service.RoleService;
import io.medalytics.onlinelearningplatform.util.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;
import java.util.stream.Collectors;


@RestController
@RequestMapping(path = "/api/v1/authenticate")
public class AdminUserController {

    private final Logger log = LoggerFactory.getLogger(AdminUserController.class);

    private CustomUserDetailsService userDetailsService;
    private RoleService roleService;
    private AuthenticationManager authenticationManager;
    private JwtUtil jwtUtil;
    private PasswordEncoder passwordEncoder;

    @Value("${application.jwt.expiration}")
    private int tokenExpirationPeriod;
    @Value("${application.jwt.secretKey}")
    private String secretKey;

    @Autowired
    public AdminUserController(CustomUserDetailsService userDetailsService, RoleService roleService, AuthenticationManager authenticationManager, JwtUtil jwtUtil, PasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.roleService = roleService;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping(path = "/sign-up", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("permitAll()")
    public ResponseEntity<User> signUp(@RequestBody UserSignUpRequest request){

        userDetailsService.userExist(request.getUsername());

        Set<Role> roles = request.getRoleType()
                .stream()
                .map(roleType -> roleService.findByName(roleType))
                .collect(Collectors.toSet());
        return ResponseEntity.ok(
                userDetailsService.save(
                        User.builder()
                                .firstName(request.getFirstName())
                                .lastName(request.getLastName())
                                .username(request.getUsername())
                                .email(request.getEmail())
                                .password(passwordEncoder.encode(request.getPassword()))
                                .roles(roles)
                                .build()
                )
        );
    }

    @PostMapping(path = "/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("permitAll()")
    public ResponseEntity<?> getAuthenticationToken(@RequestBody UsernamePasswordAuthenticationRequest request) throws AuthenticationException, BadCredentialsException {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
        String token  = jwtUtil.generateToken(userDetails);

        return new ResponseEntity<>(
                new UsernamePasswordAuthenticationResponse(token),
                HttpStatus.OK
        );
    }
}
