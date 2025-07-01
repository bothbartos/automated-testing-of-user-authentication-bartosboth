package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import model.User;
import pages.DashboardPage;
import pages.LoginPage;
import pages.RegistrationPage;
import utils.DriverManager;
import utils.TestDataReader;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class AuthenticationSteps {
    private LoginPage loginPage;
    private RegistrationPage registrationPage;
    private DashboardPage dashboardPage;
    private User currentUser;

    private LoginPage getLoginPage() {
        if (loginPage == null) {
            loginPage = new LoginPage(DriverManager.getDriver());
        }
        return loginPage;
    }

    private RegistrationPage getRegistrationPage() {
        if (registrationPage == null) {
            registrationPage = new RegistrationPage(DriverManager.getDriver());
        }
        return registrationPage;
    }

    private DashboardPage getDashboardPage() {
        if (dashboardPage == null) {
            dashboardPage = new DashboardPage(DriverManager.getDriver());
        }
        return dashboardPage;
    }

    //Login steps
    @Given("I am on the login page")
    public void userIsOnTheLoginPage() {
        DriverManager.getDriver().get("https://practice.expandtesting.com/login");
        getLoginPage().dismissCookieConsent();
    }

    @When("I enter the login credentials {string} and {string}")
    public void userEntersLoginCredentials(String username, String password) {
        getLoginPage().enterUsername(username);
        getLoginPage().enterPassword(password);
    }

    @When("I submit the login form")
    public void userSubmitsLoginForm() {
        getLoginPage().clickLoginButton();
    }

    @Then("I am on the dashboard page")
    public void userIsOnDashboardPage() {
        assertThat(getDashboardPage().isSecureAreaDisplayed()).isTrue();
    }

    @Then("I should see welcome message {string}")
    public void userShouldSeeWelcomeMessage(String message) {
        String welcomeMessage = getDashboardPage().getWelcomeMessage();
        assertThat(welcomeMessage).containsIgnoringCase(message);
    }

    //Registration steps

    @Given("I am on registration page")
    public void userIsOnRegistrationPage() {
        DriverManager.getDriver().get("https://practice.expandtesting.com/register");
        getRegistrationPage().dismissCookieConsent();
    }

    @When("I fill out registration form with {string}, {string} and {string}")
    public void userFillsOutRegistrationFormWithAnd(String username, String password, String passwordConfirmation) {
        getRegistrationPage().enterUsername(username);
        getRegistrationPage().enterPassword(password);
        getRegistrationPage().enterConfirmPassword(passwordConfirmation);
    }

    @When("I submit the registration form")
    public void userSubmitsTheRegistrationForm() {
        getRegistrationPage().clickRegisterButton();
    }

    @Then("I should see successful registration message on login page")
    public void userShouldSeeSuccessfulRegistrationMessage() {
        String alertMessage = getLoginPage().getAlertMessage();
        assertThat(alertMessage).containsIgnoringCase("Successfully registered, you can log in now.");
    }

    @Then("Correct error message is displayed {string}")
    public void errorMessageIsDisplayed(String message) {
        String actualMessage = getRegistrationPage().getErrorMessage();
        assertThat(actualMessage).containsIgnoringCase(message);
    }

    //Logout steps
    @When("I click on the logout button")
    public void clickLogoutButton() {
        getDashboardPage().clickLogout();
    }

    @Then("I should be redirected to the login page")
    public void verifyRedirectToLoginPage() {
        assertThat(DriverManager.getDriver().getCurrentUrl()).contains("login");
    }

    @Then("I should see the login form")
    public void verifyLoginFormIsVisible() {
        assertThat(getLoginPage().isLoginFormDisplayed()).isTrue();
    }

    // Data-driven test steps
    @When("I register with invalid user {int} from JSON")
    public void registerWithSpecificInvalidUserFromJSON(int userIndex) {
        List<User> invalidUsers = TestDataReader.readUsersFromJSON("users.json");

        if (userIndex >= 0 && userIndex < invalidUsers.size()) {
            this.currentUser = invalidUsers.get(userIndex);
            getRegistrationPage().register(currentUser.username(), currentUser.password(), currentUser.passwordConfirmation());

        }
    }

    @Then("Correct registration error message is displayed from JSON")
    public void correctErrorMessageIsDisplayedFromJSON() {
        String actualMessage = getRegistrationPage().getErrorMessage();
        assertThat(actualMessage).containsIgnoringCase(this.currentUser.errorMessage());
    }

}
