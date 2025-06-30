package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class LoginPage extends BasePage {

    @FindBy(name = "username")
    private WebElement usernameField;
    @FindBy(name = "password")
    private WebElement passwordField;
    @FindBy(xpath = "//button[@type='submit']")
    private WebElement loginButton;

    @FindBy(id = "flash")
    private WebElement errorMessage;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void enterUsername(String username) {
        waitForElementToBeVisible(usernameField);
        usernameField.clear();
        usernameField.sendKeys(username);
    }

    public void enterPassword(String password) {
        waitForElementToBeVisible(passwordField);
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    public void clickLoginButton() {
        waitForElementToBeClickable(loginButton);
        loginButton.click();
    }

    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
    }

    public String getErrorMessage() {
        waitForElementToBeVisible(errorMessage);
        return errorMessage.getText();
    }

    public boolean isLoginFormDisplayed() {
        return usernameField.isDisplayed() && passwordField.isDisplayed() && loginButton.isDisplayed();
    }

    public void verifyLoginPageIsDisplayed() {
        assertThat(getCurrentUrl().contains("login"));
    }
}
