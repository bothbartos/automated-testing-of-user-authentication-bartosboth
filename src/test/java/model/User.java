package model;

public class User {
    private String username;
    private String password;
    private String passwordConfirmation;
    private String errorMessage;
    public User() {}

    public User(String username, String password, String errorMessage, String passwordConfirmation) {
        this.username = username;
        this.password = password;
        this.errorMessage = errorMessage;
        this.passwordConfirmation = passwordConfirmation;
    }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getErrorMessage() { return errorMessage; }
    public void setErrorMessage(String errorMessage) { this.errorMessage = errorMessage; }

    public String getPasswordConfirmation() { return passwordConfirmation; }
    public void setPasswordConfirmation(String passwordConfirmation) { this.passwordConfirmation = passwordConfirmation; }

}

