package io.medalytics.onlinelearningplatform.controller;

import io.medalytics.onlinelearningplatform.model.Course;
import io.medalytics.onlinelearningplatform.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public List<Course> getALlCourses(){
        return courseService.findAllCourses();
    }

    @GetMapping(path = "/{param}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Course> getCoursesByParameter(@PathVariable("param") String parameter) {
        return courseService.findCourseBySearchParameter(parameter);
    }
}
