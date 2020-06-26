package io.medalytics.onlinelearningplatform.service;

import io.medalytics.onlinelearningplatform.repository.StudentRepository;
import io.medalytics.onlinelearningplatform.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> findAllStudents() {
        return studentRepository.findAll();
    }
}
