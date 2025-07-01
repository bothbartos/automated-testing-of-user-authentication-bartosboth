package model;

public record User(
        String username,
        String password,
        String passwordConfirmation,
        String errorMessage
) {
}

