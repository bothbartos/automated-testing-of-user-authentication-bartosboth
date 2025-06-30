package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DashboardPage extends BasePage{
    @FindBy(id = "flash")
    private WebElement alert;
    @FindBy(id = "username")
    private WebElement welcomeMessageWithUsername;
    @FindBy(css = "a[href='/logout']")
    private WebElement logoutButton;

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    public String getWelcomeMessage() {
        waitForElementToBeVisible(welcomeMessageWithUsername);
        return welcomeMessageWithUsername.getText();
    }

    public void clickLogout() {
        waitForElementToBeClickable(logoutButton);
        logoutButton.click();
    }

    public boolean isSecureAreaDisplayed() {
        return getCurrentUrl().contains("secure");
    }


}
