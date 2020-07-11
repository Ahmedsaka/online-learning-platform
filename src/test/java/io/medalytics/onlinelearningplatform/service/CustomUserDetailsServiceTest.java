package io.medalytics.onlinelearningplatform.service;

import io.medalytics.onlinelearningplatform.dao.UserDao;
import io.medalytics.onlinelearningplatform.model.Role;
import io.medalytics.onlinelearningplatform.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CustomUserDetailsServiceTest {

    @InjectMocks
    private CustomUserDetailsService userServiceDetails;
    @Mock
    private UserDao userDao;

    private User user;

    private Role role_1;

    @BeforeEach
    void setUp() {
        role_1 = new Role("ROLE_STUDENT", "Student");
        Set<Role> roles = new HashSet<>();
        roles.add(role_1);
        user = User.builder()
                .firstName("Ahmed")
                .lastName("Saka")
                .username("ahmedsaka")
                .email("ahmedsaka91@gmail.com")
                .password("password")
                .roles(roles)
                .build();

        Mockito.when(userDao.findByUsername(user.getUsername()))
                .thenReturn(java.util.Optional.ofNullable(user));

        Mockito.when(userDao.save(user))
                .thenReturn(user);

        Mockito.when(userServiceDetails.loadUserByUsername(user.getUsername()))
                .thenReturn(new CustomUserDetails(user));

        Mockito.when(userServiceDetails.save(user))
                .thenReturn(user);
    }

    @Test
    void loadUserByUsername() {
        Role role_2 = new Role("ROLE_INSTRUCTOR", "Instructor");
        Set<Role> roles = new HashSet<>();
        roles.add(role_2);

        User user1 = User.builder()
                .firstName("Ahmed")
                .lastName("Saka")
                .username("marelli")
                .email("ahmedsaka91@gmail.com")
                .password("password")
                .roles(roles)
                .build();
        assertEquals(userServiceDetails.loadUserByUsername("ahmedsaka"), new CustomUserDetails(user));
        assertNotEquals(userServiceDetails.loadUserByUsername("ahmedsaka"), new CustomUserDetails(user1));
    }

    @Test
    void save() {

    }
}