package io.medalytics.onlinelearningplatform.service;

import io.medalytics.onlinelearningplatform.model.Course;
import io.medalytics.onlinelearningplatform.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    private CourseRepository courseRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public List<Course> findAllCourses(){
        return courseRepository.findAll();
    }

    public List<Course> findCourseBySearchParameter(String keyword) {
        return courseRepository.findByCourseNameContains(keyword);
    }
}
