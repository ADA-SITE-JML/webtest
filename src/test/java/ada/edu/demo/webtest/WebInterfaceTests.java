package ada.edu.demo.webtest;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class WebInterfaceTests {

	@Autowired
	private WebDriver webDriver;

	@LocalServerPort
	private int port;

	@Test
	@Order(1)
	@DisplayName("Create a user")
	public void CreateUser() {
		webDriver.get("http://localhost:"+port+"/student/new");

		WebElement studentIdInput = webDriver.findElement(By.id("studentId"));
		WebElement firstNameInput = webDriver.findElement(By.id("firstName"));
		WebElement lastNameInput = webDriver.findElement(By.id("lastName"));
		WebElement emailInput = webDriver.findElement(By.id("email"));

		// Check if such a field exists
		assertNotNull(firstNameInput);

		studentIdInput.sendKeys("1");
		firstNameInput.sendKeys("Jamal");
		lastNameInput.sendKeys("Hasanov");
		emailInput.sendKeys("jhasanov@ada.edu.az");

		// Find and submit the form (assuming there's a submit button with a specific attribute)
		WebElement submitButton = webDriver.findElement(By.id("submit"));
		submitButton.click();
	}

	@Test
	@Order(2)
	@DisplayName("Check the created user")
	public void CheckUser() {
		// Check if the student is added
		webDriver.get("http://localhost:"+port+"/student/list");
		System.out.println("OKI");
		List<WebElement> bodyElementFName = webDriver.findElements(By.xpath("//a[contains(text(), 'Jamal')]"));
		List<WebElement> bodyElementLName = webDriver.findElements(By.xpath("//a[contains(text(), 'Hasanov')]"));

		// Check if the text "Jamal" is present in the page content
		assertNotNull(bodyElementFName);
		assertNotNull(bodyElementLName);
	}

}
