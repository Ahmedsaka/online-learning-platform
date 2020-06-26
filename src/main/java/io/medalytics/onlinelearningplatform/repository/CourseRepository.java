package io.medalytics.onlinelearningplatform.repository;

import io.medalytics.onlinelearningplatform.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

//   @Query(value = "select c from Courses c where c.courseName like %keyword%")
//    public List<Course> find(@Param("keyword") String keyword);

    public List<Course> findByCourseNameContains(String keyword);
}
