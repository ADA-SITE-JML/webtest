package ada.edu.demo.webtest.config;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
public class SeleniumConfig {

    @Bean
    public WebDriver webDriver() {
        // Configure Chrome to run in headless mode
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--headless");

        WebDriverManager.firefoxdriver().setup();
        return new FirefoxDriver(options);
//        return new FirefoxDriver();
    }
}
