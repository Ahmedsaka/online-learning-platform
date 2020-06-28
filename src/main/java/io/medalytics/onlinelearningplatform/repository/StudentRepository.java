package io.medalytics.onlinelearningplatform.repository;


import io.medalytics.onlinelearningplatform.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    public Student findByEmail(String email);
}
