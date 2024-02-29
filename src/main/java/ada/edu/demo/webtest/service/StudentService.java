package ada.edu.demo.webtest.service;


import ada.edu.demo.webtest.entity.Course;
import ada.edu.demo.webtest.entity.Student;
import ada.edu.demo.webtest.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepo;


    public Iterable<Student> getStudentList() {
        Iterable<Student> students = studentRepo.findAll();
        return students;
    }

    public Iterable<Student> getActiveStudents() {
        Iterable<Student> students = studentRepo.findAllActive();
        return students;
    }

    public Student getStudentById(Integer id) {
        Optional<Student> result = studentRepo.findById(id);
        Student student = null;
        if (result.isPresent()) {
            student = result.get();
        }
        return student;
    }

    public Iterable<Student> getStudentByName(String name) {
        Iterable<Student> students = studentRepo.findByNameCase("%"+name+"%");
        return students;
    }

    public Student saveStudent(Student student) {
        System.out.println(student);
        return studentRepo.save(student);
    }

    public List<Student> getStudentsByNames(String firstName,String lastName){
        return studentRepo.findByFirstNameOrLastNameIgnoreCase(firstName,lastName);
    }

    public List<Student> getStudentByEitherName(String firstName,String lastName) {
        Iterable<Student> students = studentRepo.findAll();
        List<Student> result = StreamSupport.stream(students.spliterator(), false)
                .filter(s -> s.getFirstName().equals(firstName) || s.getLastName().equals(lastName))
                .collect(Collectors.toList());
        return result;
    }

}
