package io.medalytics.onlinelearningplatform.service;

import io.medalytics.onlinelearningplatform.dao.StudentDao;
import io.medalytics.onlinelearningplatform.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private StudentDao studentDao;

    @Autowired
    public StudentService(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    public List<Student> findAllStudents() {
        return studentDao.findAll();
    }
}
