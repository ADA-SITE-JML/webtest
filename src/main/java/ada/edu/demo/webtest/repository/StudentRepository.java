package ada.edu.demo.webtest.repository;

import ada.edu.demo.webtest.entity.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StudentRepository extends CrudRepository<Student,Integer> {
    List<Student> findByFirstNameOrLastNameIgnoreCase(String firstName, String lastName);

    @Query("Select s from Student s where s.availTill is null")
    List<Student> findAllActive();

    @Query("Select s from Student s where s.availTill is null and s.firstName like %:name%")
    List<Student> findByName(String name);

    @Query(value = "SELECT * FROM TELEBELER WHERE AVAIL_TILL IS NULL AND UPPER(FIRST_NAME) like UPPER(:name)",nativeQuery = true)
    List<Student> findByNameCase(String name);

}


