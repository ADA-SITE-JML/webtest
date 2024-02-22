package ada.edu.demo.webtest;

import ada.edu.demo.webtest.entity.Course;
import ada.edu.demo.webtest.entity.Student;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class UnitTests {

	@Autowired
	private WebDriver webDriver;

	@Test
	@DisplayName("The number of courses shall correspond to the added courses")
	void testTotalCourses() {
		List<Course> courseList = new ArrayList<>();

		Integer courseCnt = (int)(Math.random() * 20);

		for (int i = 0; i< courseCnt; i++) {
			Course c = new Course();
			courseList.add(c);
		}

		Student s = new Student();
		s.setCourses(courseList);

		assert (courseCnt == s.getCourses().size());
	}
	@Test
	@DisplayName("The total credits shall correspond to the sum of the added credits")
	void testCreditCalculation() {
		List<Course> courseList = new ArrayList<>();

		Integer courseCnt = (int)(Math.random() * 20);
		Integer testCreds = 0;

		for (int i = 0; i< courseCnt; i++) {
			Integer rndCred = (int)(Math.random() * 5);
			Course c = new Course();
			c.setCredits(rndCred);
			courseList.add(c);
			testCreds += rndCred;
		}

		Student s = new Student();
		s.setCourses(courseList);

		Integer totalCredits = s.getTotalCredits();
		assert (totalCredits == testCreds);
	}

}
