package io.medalytics.onlinelearningplatform.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.medalytics.onlinelearningplatform.model.request.UsernamePasswordAuthenticationRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;

/*
*The reason for this class is to filter every request and validate Jwt
* */
@Component
public class JwtUsernameAndPasswordFilter extends UsernamePasswordAuthenticationFilter {
    private final Logger log = LoggerFactory.getLogger(JwtUsernameAndPasswordFilter.class);
    private final AuthenticationManager authenticationManager;

    public JwtUsernameAndPasswordFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Value("${jwt.token.expiration}")
    private int tokenExpirationPeriod;
    @Value("${jwt.token.secretKey}")
    private String secretKey;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
//            Extract the json string into UsernamePasswordAuthenticationRequest object
            UsernamePasswordAuthenticationRequest authenticationRequest = new ObjectMapper()
                    .readValue(request.getInputStream(),UsernamePasswordAuthenticationRequest.class);
//          Verify the principal/username and credentials/password if exist/correct
            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    authenticationRequest.getUsername(),
                    authenticationRequest.getPassword()
            );
//          Return authentication object
            return authenticationManager.authenticate(authentication);
        } catch (IOException e) {
            log.error(String.format("Error %s occurred", e.getMessage()), e);
        }

        return super.attemptAuthentication(request, response);

    }

    @Override
    protected void successfulAuthentication(
            HttpServletRequest request, HttpServletResponse response,
            FilterChain chain,
            Authentication authResult
    ) throws IOException, ServletException {
//        Jwt String generation
            String token =  Jwts.builder()
                    .setSubject(authResult.getName()) // getName returns the principal/username
                    .claim("authorities", authResult.getAuthorities()) // getAuthorities returns the roles for the particular user
                    .setIssuedAt(new Date())//The time the token was issued
                    .setExpiration(java.sql.Date.valueOf(LocalDate.now().plusWeeks(tokenExpirationPeriod))) // Token expiration period
                    .signWith(Keys.hmacShaKeyFor(secretKey.getBytes())) // Encrypts the message with secretKey
                    .compact();

            response.addHeader("Authorization", "Bearer " + token); //Set Authorization header
        super.successfulAuthentication(request, response, chain, authResult);
    }

    @Override
    protected void unsuccessfulAuthentication(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException failed
    ) throws IOException, ServletException {
        super.unsuccessfulAuthentication(request, response, failed);
    }
}
