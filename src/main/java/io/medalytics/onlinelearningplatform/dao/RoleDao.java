package io.medalytics.onlinelearningplatform.dao;

import io.medalytics.onlinelearningplatform.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleDao extends JpaRepository<Role, Long> {

    public  Role findByName(String name);
}
