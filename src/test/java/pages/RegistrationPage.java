package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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
        safeSendKeys(usernameField, username);
    }

    public void enterPassword(String password) {
        safeSendKeys(passwordField, password);
    }

    public void enterConfirmPassword(String confirmPassword) {
        safeSendKeys(confirmPasswordField, confirmPassword);
    }

    public void clickRegisterButton() {
        safeClick(registerButton);
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
}
