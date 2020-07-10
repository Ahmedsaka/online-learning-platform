package io.medalytics.onlinelearningplatform.controller;


import io.medalytics.onlinelearningplatform.model.Student;
import io.medalytics.onlinelearningplatform.model.request.StudentRegistrationRequest;
import io.medalytics.onlinelearningplatform.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/student")
public class StudentController {

    private StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService ) {
        this.studentService = studentService;
    }

    @PostMapping(path = "/add-student", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity <Student> registerStudent(@RequestBody StudentRegistrationRequest registrationRequest) {
        return new ResponseEntity<>(
                studentService.save(
                        Student.builder()
                                .firstName(registrationRequest.getFirstName())
                                .lastName(registrationRequest.getLastName())
                                .email(registrationRequest.getEmail())
                                .password(registrationRequest.getPassword())
                                .build()
                ),
                HttpStatus.OK
        );
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Student>> findAll() {
        return new ResponseEntity<>(
                studentService.findAll(),
                HttpStatus.OK
        );
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(path = "/find-student/{id}")
    public ResponseEntity<Student> findStudentById(@PathVariable long id) throws Exception {
        return new ResponseEntity<>(
                studentService.findById(id),
                HttpStatus.OK
        );
    }
}
