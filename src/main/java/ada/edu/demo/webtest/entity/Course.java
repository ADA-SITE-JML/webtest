package ada.edu.demo.webtest.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name="COURSES")
public class Course {
    @Id
    Integer courseId;

    String courseName;

    @ManyToMany(mappedBy = "courses")
    List<Student> students;
}
