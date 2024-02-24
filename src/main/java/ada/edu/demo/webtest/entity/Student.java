package ada.edu.demo.webtest.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="TELEBELER")
public class Student {
    @Id
    @Column(name="ST_ID")
    @NotNull(message="Ayibdir axi, niye ID vermirsen....")
    Integer studentId;

    @Size(min=2,max=20)
    String firstName;

    @Column(name="SOYAD")
    @Size(min=2,max=20)
    String lastName;

    @Email(message="baxan yoxdur gerek ne geldi yazasan?")
    String email;

    @Column(nullable = false)
    Date availSince = new Date();

    Date availTill;

    @ManyToMany
    @JoinTable(name="ENROLLMENTS",
                    joinColumns = @JoinColumn(name = "ST_ID"),
                    inverseJoinColumns = @JoinColumn(name = "COURSE_ID"))
    List<Course> courses;


    public Integer getTotalCredits() {
        Integer credits = 0;

        for (Course course : courses) {
            credits += course.getCredits();
        }

        return credits;
    }
}

