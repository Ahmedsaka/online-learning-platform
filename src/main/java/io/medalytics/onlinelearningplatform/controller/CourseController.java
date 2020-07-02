package io.medalytics.onlinelearningplatform.controller;

import io.medalytics.onlinelearningplatform.model.Course;
import io.medalytics.onlinelearningplatform.model.request.CourseCreationRequest;
import io.medalytics.onlinelearningplatform.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/course")
public class CourseController {

    private CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping(path = "/")
    public List<Course> getAllCourses(){
        return courseService.findAllCourses();
    }

    @GetMapping(path = "/findCourseByParameter/{param}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Course> getCoursesByParameter(@PathVariable("param") String parameter) {
        return courseService.findCourseBySearchParameter(parameter);
    }

//    @PreAuthorize("hasRole('ROLE_STUDENT') or hasRole('ROLE_INSTRUCTOR' or hasRole('ROLE_ADMIN'))")
    @GetMapping(path = "/findCourseByInstructorName/{param}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Course> findCourseByInstructorName(@PathVariable("param") String parameter) {
        return courseService.findCourseByInstructorName(parameter);
    }

    @PreAuthorize("hasRole('ROLE_INSTRUCTOR')")
    @PostMapping(value = "/addCourse", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Course save(@RequestBody CourseCreationRequest request) {
        return courseService.save(
                Course.builder()
                        .courseName(request.getCourseName())
                        .description(request.getDescription())
                        .instructorName(request.getInstructorName())
                        .build()
        );

    }
}
