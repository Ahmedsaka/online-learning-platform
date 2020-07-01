package io.medalytics.onlinelearningplatform.controller;

import io.medalytics.onlinelearningplatform.dao.StudentDao;
import io.medalytics.onlinelearningplatform.model.Student;
import io.medalytics.onlinelearningplatform.model.request.StudentRegistrationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/student")
public class StudentController {

    private StudentDao studentDao;

    @Autowired
    public StudentController(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    @PostMapping(path = "/addStudent", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Student registerStudent(@RequestBody StudentRegistrationRequest registrationRequest) {
        return studentDao.save(
                Student.builder()
                .firstName(registrationRequest.getFirstName())
                .lastName(registrationRequest.getLastName())
                .email(registrationRequest.getEmail())
                .password(registrationRequest.getPassword())
                .build()
        );
    }
}
