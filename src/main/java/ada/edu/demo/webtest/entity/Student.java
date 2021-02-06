package ada.edu.demo.webtest.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;
import java.util.List;


@Data
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
}

