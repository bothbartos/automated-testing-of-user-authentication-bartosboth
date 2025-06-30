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
    private WebElement alertMessage;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void enterUsername(String username) {
        safeSendKeys(usernameField, username);
    }

    public void enterPassword(String password) {
        safeSendKeys(passwordField, password);
    }

    public void clickLoginButton() {
        safeClick(loginButton);
    }

    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
    }

    public String getAlertMessage() {
        waitForElementToBeVisible(alertMessage);
        return alertMessage.getText();
    }

    public boolean isLoginFormDisplayed() {
        try {
            waitForElementToBeVisible(usernameField);
            return usernameField.isDisplayed() && passwordField.isDisplayed() && loginButton.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void verifyLoginPageIsDisplayed() {
        assertThat(getCurrentUrl()).contains("login");
    }
}
