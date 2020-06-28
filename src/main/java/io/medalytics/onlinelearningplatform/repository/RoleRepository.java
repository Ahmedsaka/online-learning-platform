package io.medalytics.onlinelearningplatform.repository;

import io.medalytics.onlinelearningplatform.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
