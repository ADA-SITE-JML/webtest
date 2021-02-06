package ada.edu.demo.webtest.repository;

import ada.edu.demo.webtest.entity.Course;
import org.springframework.data.repository.CrudRepository;

public interface CourseRepository extends CrudRepository<Course,Integer> {
}
