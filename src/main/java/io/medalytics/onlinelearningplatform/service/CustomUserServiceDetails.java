package io.medalytics.onlinelearningplatform.service;

import io.medalytics.onlinelearningplatform.model.User;
import io.medalytics.onlinelearningplatform.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserServiceDetails implements UserDetailsService {

    private UserDao userDao;

    @Autowired
    public CustomUserServiceDetails(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserDetails loadUserByUsername(String emailOrUsername) throws UsernameNotFoundException {
        User user = userDao.findByUsername(emailOrUsername);
        if (user == null) throw new UsernameNotFoundException(String.format("You are not registered as a student with email %s", emailOrUsername));
        return new CustomUserDetails(user);
    }

    public User save(User user) {
        return userDao.save(user);
    }

    public boolean userExist(String username){
        if (userDao.existsByUsername(username)){
            throw new IllegalStateException(String.format("User %s already exist", username));
        }
        return true;
    }
}
