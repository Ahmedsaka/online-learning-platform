package io.medalytics.onlinelearningplatform.service;

import io.medalytics.onlinelearningplatform.dao.StudentDao;
import io.medalytics.onlinelearningplatform.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private StudentDao studentDao;

    @Autowired
    public StudentService(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    public List<Student> findAll() {
        return studentDao.findAll();
    }

    public Student save(Student student) {
        return studentDao.save(student);
    }


    public Student findById(long id) throws Exception {
        Optional<Student> studentOptional = Optional.ofNullable(studentDao.findById(id)
                .orElseThrow(() -> new Exception(String.format("Student with id %s was not found", id))));
        return studentOptional.get();
    }
}
