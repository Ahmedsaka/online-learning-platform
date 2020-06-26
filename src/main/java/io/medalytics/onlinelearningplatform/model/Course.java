package io.medalytics.onlinelearningplatform.model;

import lombok.*;

import javax.persistence.*;


@Entity
@Table(name = "Courses")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
@ToString
public class Course extends BaseModel {

    @Column(name = "course_name")
    private String courseName;
    @Column(name = "description")
    private String description;
    @Column(name = "instructor_name")
    private String instructorName;
}
