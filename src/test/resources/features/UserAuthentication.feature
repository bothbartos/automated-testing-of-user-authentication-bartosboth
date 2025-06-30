@authentication
Feature: User Authentication
  As a user of the practice application
  I want to register, login, and manage my session
  So that I can access the secure area safely

  @login @negative
  Scenario: Login with invalid credentials
    Given I am on the login page
    When I enter the login credentials "practice" and "invalidPassword!"
    And I submit the login form
    Then Correct error message is displayed "Your password is invalid!"

  @session @positive
  Scenario: Complete login-logout cycle
    Given I am on the login page
    When I enter the login credentials "practice" and "SuperSecretPassword!"
    And I submit the login form
    Then I am on the dashboard page
    When I click on the logout button
    Then I should be redirected to the login page
    And I should see the login form



  @json-driven @individual @registration @negative
  Scenario Outline: Registration with specific invalid data from JSON
    Given I am on registration page
    When I register with invalid user <userIndex> from JSON
    Then Correct registration error message is displayed from JSON

    Examples:
      | userIndex |
      | 0         |
      | 1         |
      | 2         |
      | 3         |


  @registration @positive
  Scenario: Registration and Login with Valid Data
    Given I am on registration page
    When I fill out registration form with "testUser0630", "testPass123" and "testPass123"
    And I submit the registration form
    Then I should see successful registration message on login page
    When I enter the login credentials "testUser0630" and "testPass123"
    And I submit the login form
    Then I am on the dashboard page
    And I should see welcome message "testUser0630"