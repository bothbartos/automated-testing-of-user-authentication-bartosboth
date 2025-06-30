package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class DriverManager {
    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    public static void setDriver(String browser) {
        WebDriver driver = createDriver(browser);
        driverThreadLocal.set(driver);
    }

    public static WebDriver getDriver() {
        return driverThreadLocal.get();
    }

    public static void quitDriver() {
        WebDriver driver = driverThreadLocal.get();
        if (driver != null) {
            driver.quit();
            driverThreadLocal.remove();
        }
    }

    private static WebDriver createDriver(String browser) {
        return switch (browser.toLowerCase()){
            case "firefox" -> {
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions options = new FirefoxOptions();

                if (System.getProperty("os.name").toLowerCase().contains("mac")) {
                    options.addArguments("--no-sandbox");
                    options.addArguments("--disable-dev-shm-usage");
                    options.setBinary("/Applications/Firefox.app/Contents/MacOS/firefox");
                }

                options.addArguments("--disable-blink-features=AutomationControlled");
                options.addArguments("--disable-extensions");
                yield new FirefoxDriver(options);
            }
            case "chrome" -> {
                WebDriverManager.chromedriver().setup();
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--disable-blink-features=AutomationControlled");
                options.addArguments("--disable-extensions");
                options.addArguments("--no-sandbox");
                options.addArguments("--disable-dev-shm-usage");
                yield new ChromeDriver(options);
            }
            case "edge" -> {
                WebDriverManager.edgedriver().setup();
                yield new EdgeDriver();
            }
            default -> throw new IllegalStateException("Unexpected value: " + browser);
        };
    }
}
