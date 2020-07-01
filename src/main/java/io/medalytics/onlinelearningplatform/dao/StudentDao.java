package io.medalytics.onlinelearningplatform.dao;


import io.medalytics.onlinelearningplatform.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentDao extends JpaRepository<Student, Long> {

    public Student findByEmail(String email);
}
