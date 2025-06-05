package com.bloomless.core.accountManagement.exceptions;

public class PasswordNotFound extends RuntimeException {
    public PasswordNotFound(String message) {
        super(message);
    }
}
