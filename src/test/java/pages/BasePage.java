package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;

public abstract class BasePage {
    protected WebDriver driver;
    protected FluentWait<WebDriver> fluentWait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.fluentWait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NoSuchElementException.class)
                .ignoring(ElementNotInteractableException.class);
        PageFactory.initElements(driver, this);
    }

    protected void scrollIntoView(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;

        js.executeScript("arguments[0].scrollIntoView({behavior: 'auto', block: 'center'});", element);

        FluentWait<WebDriver> scrollWait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(5))
                .pollingEvery(Duration.ofMillis(100))
                .ignoring(Exception.class);

        scrollWait.until(driver -> {
            Boolean isVisible = (Boolean) js.executeScript(
                    "var rect = arguments[0].getBoundingClientRect();" +
                            "return (rect.top >= 0 && rect.left >= 0 && " +
                            "rect.bottom <= window.innerHeight && rect.right <= window.innerWidth);",
                    element);

            return isVisible && element.isDisplayed() && element.isEnabled();
        });
    }


    protected void waitForElementToBeClickable(WebElement element) {
        scrollIntoView(element);
        fluentWait.until(ExpectedConditions.elementToBeClickable(element));
    }

    protected void waitForElementToBeVisible(WebElement element) {
        scrollIntoView(element);
        fluentWait.until(ExpectedConditions.visibilityOf(element));
    }

    protected void safeClick(WebElement element) {
        waitForElementToBeClickable(element);
        element.click();
    }

    protected void safeSendKeys(WebElement element, String text) {
        waitForElementToBeVisible(element);
        element.clear();
        element.sendKeys(text);
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

     public void dismissCookieConsent() {
        try {
            FluentWait<WebDriver> cookieWait = new FluentWait<>(driver)
                    .withTimeout(Duration.ofSeconds(3))
                    .pollingEvery(Duration.ofMillis(500))
                    .ignoring(NoSuchElementException.class);

            WebElement element = cookieWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[id*='accept']")));
            element.click();
            System.out.println("Cookie popup dismissed");

        } catch (Exception e) {
            System.out.println("No cookie popup found");
        }
    }

}
