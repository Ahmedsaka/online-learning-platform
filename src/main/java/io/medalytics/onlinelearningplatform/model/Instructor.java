package io.medalytics.onlinelearningplatform.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;


@Entity
@Table(name = "Instructors")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Instructor extends BaseModel{
    private String username;
    private String email;
    private String password;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Course> courses;
}
