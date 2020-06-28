package io.medalytics.onlinelearningplatform.service;

import io.medalytics.onlinelearningplatform.model.Student;
import io.medalytics.onlinelearningplatform.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceDetails implements UserDetailsService {

    private StudentRepository studentRepository;

    @Autowired
    public StudentServiceDetails(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Student student = studentRepository.findByEmail(s);
        if (student == null) throw new UsernameNotFoundException(String.format("You are not registered as a student with email %s", s));
        return new StudentDetails(student);
    }
}
