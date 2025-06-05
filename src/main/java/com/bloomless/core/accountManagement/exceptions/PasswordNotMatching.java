package com.bloomless.core.accountManagement.exceptions;

public class PasswordNotMatching extends RuntimeException {
    public PasswordNotMatching(String message) {
        super(message);
    }
}
