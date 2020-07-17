package io.medalytics.onlinelearningplatform.service;

import io.medalytics.onlinelearningplatform.dao.UserDao;
import io.medalytics.onlinelearningplatform.model.CustomUserDetails;
import io.medalytics.onlinelearningplatform.model.Role;
import io.medalytics.onlinelearningplatform.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class CustomUserDetailsServiceTest {
    private final Logger log = LoggerFactory.getLogger(CustomUserDetailsServiceTest.class);

    @InjectMocks
    private CustomUserDetailsService userServiceDetails;
    @Mock
    private UserDao userDao;

    private User user;
    private User user1;

    @BeforeEach
    void setUp() {
        Role role_1 = new Role("ROLE_STUDENT", "Student");
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

        Role role_2 = new Role("ROLE_INSTRUCTOR", "Instructor");
        Set<Role> roles1= new HashSet<>();
        roles.add(role_2);

        user1 = User.builder()
                .firstName("Ahmed")
                .lastName("Saka")
                .username("marelli")
                .email("ahmedsaka91@gmail.com")
                .password("password")
                .roles(roles1)
                .build();

        Mockito.lenient().when(userDao.findByUsername(user1.getUsername()))
                .thenReturn(Optional.of(user1));

        Mockito.lenient().when(userDao.findByUsername(user.getUsername()))
                .thenReturn(Optional.of(user));

        Mockito.lenient().when(userDao.findByUsername(user1.getUsername()))
                .thenThrow(UsernameNotFoundException.class);

        Mockito.lenient().when(userDao.save(user))
                .thenReturn(user);

        Mockito.lenient().when(userDao.existsByUsername(user.getUsername()))
                .thenReturn(true);
    }

    @Test
    void loadUserByUsername() {
        CustomUserDetails details = new CustomUserDetails(user);
        CustomUserDetails userDetails = userServiceDetails.loadUserByUsername("ahmedsaka");

        assertEquals(userDetails.getUsername(), details.getUsername());
        assertEquals(userDetails.getAuthorities(), details.getAuthorities());
        assertEquals(userDetails.getPassword(), details.getPassword());
        assertEquals(userDetails.getFirstName(), details.getFirstName());
        assertEquals(userDetails.getLastName(), details.getLastName());
    }

    @Test
    void userNotFoundTest(){
        assertThrows(UsernameNotFoundException.class, ()-> userServiceDetails.loadUserByUsername("ttt"));
    }

    @Test
    void testSave() {
        Role role_1 = new Role("ROLE_STUDENT", "Student");
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

        assertNull(userServiceDetails.save(user));
    }

    @Test
    void userExist() {
        assertTrue(userServiceDetails.userExist("ahmedsaka"));
        assertFalse(userServiceDetails.userExist("ttt"));
    }
}