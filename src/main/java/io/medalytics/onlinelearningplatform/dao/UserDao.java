package io.medalytics.onlinelearningplatform.dao;

import io.medalytics.onlinelearningplatform.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<User, Long> {
    public User findByUsername(String username);
}
