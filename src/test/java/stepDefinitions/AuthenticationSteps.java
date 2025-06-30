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

    @Given("User is on the login page")
    public void userIsOnTheLoginPage() {
        DriverManager.getDriver().get("https://practice.expandtesting.com/login");
        getLoginPage().dismissCookieConsent();
    }

    @When("User enters login credentials {string} and {string}")
    public void userEntersLoginCredentials(String username, String password) {
        getLoginPage().enterUsername(username);
        getLoginPage().enterPassword(password);
    }

    @When("User submits login form")
    public void userSubmitsLoginForm() {
        getLoginPage().clickLoginButton();
    }

    @Then("User is on dashboard page")
    public void userIsOnDashboardPage() {
        assertThat(getDashboardPage().isSecureAreaDisplayed()).isTrue();
    }

    @Then("User should see welcome message {string}")
    public void userShouldSeeWelcomeMessage(String message) {
        String welcomeMessage = getDashboardPage().getWelcomeMessage();
        assertThat(welcomeMessage).containsIgnoringCase(message);
    }

    @Then("User should see error message {string}")
    public void userShouldSeeErrorMessage(String message) {
        String actualMessage = getLoginPage().getAlertMessage();
        assertThat(actualMessage).containsIgnoringCase(message);
    }

    @Then("User should remain on login page")
    public void userShouldRemainOnLoginPage() {
        assertThat(getLoginPage().isLoginFormDisplayed()).isTrue();
    }

    //Registration steps

    @Given("User is on registration page")
    public void userIsOnRegistrationPage() {
        DriverManager.getDriver().get("https://practice.expandtesting.com/register");
        getRegistrationPage().dismissCookieConsent();
    }

    @When("User fills out registration form with {string}, {string} and {string}")
    public void userFillsOutRegistrationFormWithAnd(String username, String password, String passwordConfirmation) {
        getRegistrationPage().enterUsername(username);
        getRegistrationPage().enterPassword(password);
        getRegistrationPage().enterConfirmPassword(passwordConfirmation);
    }

    @When("User submits the registration form")
    public void userSubmitsTheRegistrationForm() {
        getRegistrationPage().clickRegisterButton();
    }

    @Then("User should see successful registration message on login page")
    public void userShouldSeeSuccessfulRegistrationMessage() {
        String currentUrl = DriverManager.getDriver().getCurrentUrl();
        assertThat(currentUrl.contains("login")).isTrue();
        String alertMessage = getLoginPage().getAlertMessage();
        assertThat(alertMessage).containsIgnoringCase(" Successfully registered, you can log in now.");
    }

    @When("User registers with taken username: {string}, password: {string} and password confirmation {string}")
    public void userRegistersWithTakenUsername(String username, String password, String passwordConfirmation) {
        getRegistrationPage().register(username, password, passwordConfirmation);
    }

    @Then("Error message is displayed")
    public void errorMessageIsDisplayed() {
        assertThat(getLoginPage().getAlertMessage()).containsIgnoringCase("Username is already taken.");
    }

    //Logout steps
    @Given("I am logged in with valid credentials")
    public void loginWithValidCredentials() {
        DriverManager.getDriver().get("https://practice.expandtesting.com/login");
        currentUser = TestDataReader.getUserByUsername("users.csv", "practice");
        if (currentUser != null) {
            getLoginPage().dismissCookieConsent();
            getLoginPage().login(currentUser.getUsername(), currentUser.getPassword());
        }
    }

    @When("I click the logout button")
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
    @When("I login with user {string} from CSV")
    public void loginWithUserFromCSV(String username) {
        currentUser = TestDataReader.getUserByUsername("users.csv", username);
        if (currentUser != null) {
            getLoginPage().login(currentUser.getUsername(), currentUser.getPassword());
        }
    }

    @When("I register with user data from JSON")
    public void registerWithUserFromJSON() {
        List<User> users = TestDataReader.readUsersFromJSON("users.json");
        if (!users.isEmpty()) {
            User user = users.getFirst();
            getRegistrationPage().register(user.getUsername(), user.getPassword(), user.getPassword());
        }
    }

}
