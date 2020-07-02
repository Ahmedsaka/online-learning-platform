package io.medalytics.onlinelearningplatform.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CourseCreationRequest {
    private String courseName;
    private String description;
    private String instructorName;
}
