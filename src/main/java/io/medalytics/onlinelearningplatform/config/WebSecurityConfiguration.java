package io.medalytics.onlinelearningplatform.config;

import io.medalytics.onlinelearningplatform.service.CustomUserServiceDetails;
import io.medalytics.onlinelearningplatform.util.JwtTokenVerifier;
import io.medalytics.onlinelearningplatform.util.JwtUsernameAndPasswordFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
@Configuration
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.parentAuthenticationManager(authenticationManagerBean())
                .authenticationProvider(daoAuthenticationProvider());
    }

    private CustomUserServiceDetails userServiceDetails;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public WebSecurityConfiguration(CustomUserServiceDetails userServiceDetails, PasswordEncoder passwordEncoder) {
        this.userServiceDetails = userServiceDetails;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/api/v1/course/*")
                .permitAll()
                .antMatchers("/api/v1/authenticate/signUp")
                .permitAll()
                .antMatchers("**/h2-console/**").hasRole("ROLE_ADMIN")
                .anyRequest()
                .authenticated()
                .and()
                .csrf()
                .disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                ;
//
        http.addFilter(new JwtUsernameAndPasswordFilter(authenticationManagerBean()))
                .addFilterAfter(new JwtTokenVerifier(), JwtUsernameAndPasswordFilter.class);
        http.headers().frameOptions().disable();
    }

    @Override
    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userServiceDetails);
        provider.setPasswordEncoder(passwordEncoder);
        return provider;
    }
}
