package io.medalytics.onlinelearningplatform.service;

import io.medalytics.onlinelearningplatform.dao.UserDao;
import io.medalytics.onlinelearningplatform.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.servlet.ModelAndView;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CustomUserServiceDetailsTest {

    @InjectMocks
    private CustomUserServiceDetails userServiceDetails;
    @Mock
    private UserDao userDao;

    private User user;

    @BeforeEach
    void setUp() {
        user = User.builder()
                .firstName("Ahmed")
                .lastName("Saka")
                .username("ahmedsaka")
                .email("ahmedsaka91@gmail.com")
                .password("password")
                .build();

        Mockito.when(userDao.findByUsername(user.getUsername()))
                .thenReturn(user);

        Mockito.when(userDao.save(user))
                .thenReturn(user);

        Mockito.when(userServiceDetails.loadUserByUsername(user.getUsername()))
                .thenReturn(new CustomUserDetails(user));

        Mockito.when(userServiceDetails.save(user))
                .thenReturn(user);
    }

    @Test
    void loadUserByUsername() {
        User user1 = User.builder()
                .firstName("Ahmed")
                .lastName("Saka")
                .username("marelli")
                .email("ahmedsaka91@gmail.com")
                .password("password")
                .build();
        assertEquals(userServiceDetails.loadUserByUsername("ahmedsaka"), new CustomUserDetails(user));
        assertNotEquals(userServiceDetails.loadUserByUsername("ahmedsaka"), new CustomUserDetails(user1));
    }

    @Test
    void save() {

    }
}