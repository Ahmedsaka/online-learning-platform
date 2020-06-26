package io.medalytics.onlinelearningplatform.repository;

import io.medalytics.onlinelearningplatform.model.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstructorRepository extends JpaRepository<Instructor, Long> {
}
