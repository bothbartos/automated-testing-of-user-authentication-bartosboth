package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class RegistrationPage extends BasePage {

    @FindBy(name = "username")
    private WebElement usernameField;
    @FindBy(name = "password")
    private WebElement passwordField;
    @FindBy(name = "confirmPassword")
    private WebElement confirmPasswordField;
    @FindBy(xpath = "//button[@type='submit']")
    private WebElement registerButton;

    @FindBy(id = "flash")
    private WebElement errorMessage;

    public RegistrationPage(WebDriver driver) {
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

    public void enterConfirmPassword(String confirmPassword) {
        waitForElementToBeVisible(confirmPasswordField);
        confirmPasswordField.clear();
        confirmPasswordField.sendKeys(confirmPassword);
    }

    public void clickRegisterButton() {
        waitForElementToBeClickable(registerButton);
        registerButton.click();
    }

    public void register(String username, String password, String confirmPassword) {
        enterUsername(username);
        enterPassword(password);
        enterConfirmPassword(confirmPassword);
        clickRegisterButton();
    }

    public String getErrorMessage() {
        waitForElementToBeVisible(errorMessage);
        return errorMessage.getText();
    }

    public boolean isRegistrationFormDisplayed() {
        return usernameField.isDisplayed()
                && passwordField.isDisplayed()
                && confirmPasswordField.isDisplayed()
                && registerButton.isDisplayed();
    }

    public void verifyRegistrationPageIsDisplayed() {
        assertThat(getCurrentUrl().contains("register"));
    }
}
