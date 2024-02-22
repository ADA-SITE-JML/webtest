package ada.edu.demo.webtest.controller;

import ada.edu.demo.webtest.entity.Course;
import ada.edu.demo.webtest.entity.Student;
import ada.edu.demo.webtest.repository.CourseRepository;
import ada.edu.demo.webtest.repository.StudentRepository;
import ada.edu.demo.webtest.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentService studentService;

    @Autowired
    CourseRepository courseRepo;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/")
    public String getIndex() {
        return "student/index";
    }

    @GetMapping("/list")
    public String getStudentList(Model model) {
        Iterable<Student> students = studentService.getStudentList();
        model.addAttribute("students",students);

        return "student/list";
    }

    @GetMapping("/list/active")
    public String getAvailableStudentList(Model model) {
        Iterable<Student> students = studentService.getActiveStudents();

        return "student/list";
    }

    @GetMapping("/id")
    public String getSingleStudent(Model model,@RequestParam Integer id) {
        List<Student> stList = new ArrayList<Student>();

        Student student = studentService.getStudentById(id);
        if (student != null)
            stList.add(student);

        model.addAttribute("students",stList);
        return "student/list";
    }

    @GetMapping("/name")
    public String getSingleStudent(Model model,@RequestParam String name) {
        Iterable<Student> students = studentService.getStudentByName(name);
        model.addAttribute("students",students);
        return "student/list";
    }

    @GetMapping("/new")
    public String showNewPage(Model model) {
        model.addAttribute("student",new Student());
        return "student/details";
    }

    @GetMapping("/update")
    public String showUpdatePage(Model model,@RequestParam Integer id) {
        Student student  = studentService.getStudentById(id);
        if (student == null)
            student = new Student();
        model.addAttribute("student",student);

        Iterable<Course> courses = courseRepo.findAll();
        model.addAttribute("allCourses",courses);

        return "student/details";
    }

    @PostMapping("/save")
    public String saveStudent(Model model,@Valid Student studentData, BindingResult br) {
        logger.debug("Entered saveStudent() method");
        if (br.hasErrors()) {
            logger.error("saveStudent() : got some errors! -> with firstName="+studentData.getFirstName());
            return "student/details";
        }

        logger.debug("saveStudent() : no errors");

        Student savedStudent = studentService.saveStudent(studentData);

        logger.info("saveStudent() : saved -> "+savedStudent.getFirstName());
        return "student/index";
    }

    @GetMapping("/search")
    public String searchStudents(Model model,@RequestParam("first") String firstName,@RequestParam("last")  String lastName) {
        List<Student> students = studentService.getStudentsByNames(firstName,lastName);
        model.addAttribute("students",students);

        return "student/list";
    }
}
