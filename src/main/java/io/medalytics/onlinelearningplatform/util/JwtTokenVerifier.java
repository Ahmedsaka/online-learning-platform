package io.medalytics.onlinelearningplatform.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import io.jsonwebtoken.security.WeakKeyException;
import io.medalytics.onlinelearningplatform.model.UserSignUpRequest;
import lombok.var;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;


public class JwtTokenVerifier extends OncePerRequestFilter {
    @Value("${application.jwt.secretKey}")
    private String secretKey;

    @Override
    protected void doFilterInternal(
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse,
            FilterChain filterChain) throws ServletException, IOException {

        String authorizationHeader = httpServletRequest.getHeader("Authorization");
//        If request does not contain an authorizationHeader, continue to the next filter....
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")){
            filterChain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }

        if (httpServletRequest.getRequestURI().equals("/api/v1/authenticate/sign-up")){
            ObjectMapper mapper = new ObjectMapper();
            UserSignUpRequest signUpRequest = mapper.readValue(
                    httpServletRequest.getInputStream(),
                    UserSignUpRequest.class
            );
            if (signUpRequest != null){
                filterChain.doFilter(httpServletRequest, httpServletResponse);
                return;
            }
        }

//      If request contains an authorizationHeader, replace the "Bearer " string with "" leaving only the token
        String token = authorizationHeader.replace("Bearer ", "");
//      Extract the claims from the token using the secretKey
        try {
            Jws<Claims> claimsJws = Jwts.parser()
                    .setSigningKey(Keys.hmacShaKeyFor(secretKey.getBytes()))
                    .parseClaimsJws(token);
            Claims body = claimsJws.getBody();
            String username = body.getSubject(); // Get the username
            var authorities = (List<Map<String, String>>) body.get("authorities");
//        Collect the roles/permission into a set of simpleGrantedAuthority
            Set<SimpleGrantedAuthority> simpleGrantedAuthorities = authorities.stream()
                    .map(authority -> new SimpleGrantedAuthority(authority.get("authority")))
                    .collect(Collectors.toSet());
//      Authenticate and save in the SecurityContextHolder
            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    username,
                    null,
                    simpleGrantedAuthorities
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
//        }catch (JwtException e){
//            throw new IllegalStateException(String.format("Token %s can not be trusted", token));
        } catch (ExpiredJwtException | WeakKeyException | UnsupportedJwtException | MalformedJwtException | SignatureException | IllegalArgumentException e) {
            e.printStackTrace();
        }
//        Pass response to the next filter in the chain
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
