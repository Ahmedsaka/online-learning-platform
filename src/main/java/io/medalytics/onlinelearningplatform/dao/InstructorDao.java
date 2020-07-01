package io.medalytics.onlinelearningplatform.dao;

import io.medalytics.onlinelearningplatform.model.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstructorDao extends JpaRepository<Instructor, Long> {
}
