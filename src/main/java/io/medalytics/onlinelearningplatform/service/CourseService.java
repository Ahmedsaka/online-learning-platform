package io.medalytics.onlinelearningplatform.service;

import io.medalytics.onlinelearningplatform.dao.CourseDao;
import io.medalytics.onlinelearningplatform.model.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    private CourseDao courseDao;

    @Autowired
    public CourseService(CourseDao courseDao) {
        this.courseDao = courseDao;
    }

    public List<Course> findAllCourses(){
        return courseDao.findAll();
    }

    public List<Course> findCourseBySearchParameter(String keyword) {
        return courseDao.findByCourseNameContains(keyword);
    }

    public List<Course> findCourseByInstructorName(String parameter) {
        return courseDao.findByInstructorName(parameter);
    }

    public Course save(Course course) {
        return courseDao.save(course);
    }
}
