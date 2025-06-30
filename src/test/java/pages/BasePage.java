package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;
import java.util.Objects;

public class BasePage {
    protected WebDriver driver;
    protected FluentWait<WebDriver> fluentWait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.fluentWait = new FluentWait<>(driver)
                .pollingEvery(Duration.ofSeconds(2))
                .withTimeout(Duration.ofSeconds(5))
                .ignoring(NoSuchElementException.class)
                .ignoring(ElementNotInteractableException.class);

        PageFactory.initElements(driver, this);
    }

    protected void waitForElementToBeClickable(WebElement element) {
        fluentWait.until(ExpectedConditions.elementToBeClickable(element));
    }

    protected void waitForElementToBeVisible(WebElement element) {
        fluentWait.until(ExpectedConditions.visibilityOf(element));
    }

    protected void waitForPageToLoad() {
        fluentWait.until(driver -> Objects.equals(((JavascriptExecutor) driver).executeScript("return document.readyState"), "complete"));
    }

    protected WebElement waitForElementPresence(By locator) {
        return fluentWait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    protected boolean waitForTextToBePresentInElement(WebElement element, String text) {
        return fluentWait.until(ExpectedConditions.textToBePresentInElement(element, text));
    }

    protected FluentWait<WebDriver> createCustomWait(int timeoutSeconds, int pollingSeconds) {
        return new FluentWait<>(driver)
                .pollingEvery(Duration.ofSeconds(pollingSeconds))
                .withTimeout(Duration.ofSeconds(timeoutSeconds))
                .ignoring(NoSuchElementException.class)
                .ignoring(ElementNotInteractableException.class);
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public String getTitle() {
        return driver.getTitle();
    }

}
