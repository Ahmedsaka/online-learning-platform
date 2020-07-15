package io.medalytics.onlinelearningplatform.service;

import io.medalytics.onlinelearningplatform.model.User;
import io.medalytics.onlinelearningplatform.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private UserDao userDao;

    @Autowired
    public CustomUserDetailsService(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserDetails loadUserByUsername(String emailOrUsername) throws UsernameNotFoundException {
        Optional<User> user = Optional.ofNullable(userDao.findByUsername(emailOrUsername)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("You are not registered as a student with email %s", emailOrUsername))));
        return new CustomUserDetails(user.get());
    }

    public User save(User user) {
        return userDao.save(user);
    }

    public boolean userExist(String username){
        return userDao.existsByUsername(username);
    }
}
