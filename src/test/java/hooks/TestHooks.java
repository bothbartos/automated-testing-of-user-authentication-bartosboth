package hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import utils.DriverManager;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

public class TestHooks {
    private WebDriver driver;

    @Before
    public void setUp() {
        String browser = System.getProperty("browser", "firefox");

        DriverManager.setDriver(browser);
        driver = DriverManager.getDriver();

        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
    }

    @After
    public void tearDown(Scenario scenario) {
        if(driver != null) {
            if(scenario.isFailed()) {
                takeScreenshot(scenario);
            }
            DriverManager.quitDriver();
        }
    }


    private void takeScreenshot(Scenario scenario) {
        try{
            TakesScreenshot ts = (TakesScreenshot) driver;
            byte[] screenshot = ts.getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "Screenshot-" + System.currentTimeMillis());
        }catch (Exception e) {
            System.err.println("Failed to capture screenshot: " + e.getMessage());
        }
    }
}
