# Automated Testing of User Authentication

A test automation framework for testing user authentication functionalities using Behavior-Driven Development (BDD) principles. This project automates registration, login, and session management scenarios on the [Practice Expand Testing](https://practice.expandtesting.com) website.

## 🎯 Project Overview

This automation framework tests the complete user authentication flow including:
- User registration with validation
- User login with valid/invalid credentials  
- Session management and logout functionality
- Error handling and validation scenarios

## 🛠️ Technology Stack

- **Java 22** - Programming language
- **Selenium WebDriver 4.33.0** - Browser automation
- **Cucumber 7.18.1** - BDD framework for Gherkin scenarios
- **JUnit 5.10.2** - Test execution and assertions
- **AssertJ 3.25.3** - Fluent assertions
- **Maven 3.x** - Dependency management and build tool
- **WebDriverManager 5.4.1** - Automatic driver management
- **Jackson 2.17.1** - JSON data processing

## 🚀 Getting Started

### Prerequisites
- Java 22 or higher
- Maven 3.6+
- Chrome/Firefox/Edge browser installed

### Installation
1. Clone the repository:
```bash
git clone git@github.com:bothbartos/automated-testing-of-user-authentication-bartosboth.git
cd automated-testing-of-user-authentication
```

2. Install dependencies:
```bash
mvn clean install
```

## 🧪 Test Execution

### Run All Tests
```bash
mvn clean test
```

### Run Specific Test Categories
```bash
# Login tests only
mvn test -Dcucumber.filter.tags="@login"

# Registration tests only
mvn test -Dcucumber.filter.tags="@registration"

# Positive scenarios only
mvn test -Dcucumber.filter.tags="@positive"

# Negative scenarios only
mvn test -Dcucumber.filter.tags="@negative"

# Data-driven tests
mvn test -Dcucumber.filter.tags="@json-driven"

```

### Browser Selection
```bash
# Run with Firefox (default)
mvn test

# Run with Chrome
mvn test -Dbrowser=chrome

# Run with Edge
mvn test -Dbrowser=edge
```

### Parallel Execution
Tests run in parallel by default (4 threads). To modify, edit `src/test/resources/junit-platform.properties`:
```bash
# Run with 3 parallel threads
mvn test -Dcucumber.execution.parallel.config.fixed.parallelism=3

# Disable parallel execution
mvn test -Dcucumber.execution.parallel.enabled=false
```

## 📋 Test Scenarios

### Login Scenarios
- ✅ Successful login with valid credentials
- ❌ Login with invalid username
- ❌ Login with invalid password
- ❌ Login with empty credentials
- 📊 Data-driven login using CSV data

### Registration Scenarios
- ✅ Successful registration with valid data
- ❌ Registration with existing username
- ❌ Registration with mismatched passwords
- ❌ Registration with empty required fields
- 📊 Data-driven registration using JSON data

### Session Management
- 🔐 Complete login-logout cycle
- 🛡️ Secure area access verification
- 🚪 Session termination validation

## 📊 Test Reports

After test execution, reports are generated in:
- `target/cucumber-reports/cucumber.html` - HTML report
- `target/cucumber-reports/cucumber.json` - JSON report
- `target/surefire-reports/` - Maven Surefire reports

## 🎯 Key Features

- **BDD Approach**: Gherkin scenarios for business-readable tests
- **Page Object Model**: Maintainable and reusable page interactions
- **Data-Driven Testing**: External CSV/JSON data sources
- **Parallel Execution**: Faster test execution with thread-safe design
- **Cross-Browser Support**: Chrome, Firefox, and Edge compatibility
- **Comprehensive Reporting**: HTML and JSON test reports
- **Robust Waits**: FluentWait implementation for stable element interactions
- **Cookie/Ad Handling**: Automatic dismissal of popups and ads

## 📝 Notes

- The framework uses **practice.expandtesting.com** as the target application
- Tests include both positive and negative scenarios as required
- External data sources (CSV/JSON) are implemented for data-driven testing
- Parallel execution is enabled for improved performance
- All bonus requirements have been implemented

[1] https://practice.expandtesting.com
