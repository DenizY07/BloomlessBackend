package com.bloomless.core.accountManagement.exceptions;

public class UsernameAlreadyExisting extends RuntimeException {
    public UsernameAlreadyExisting(String message) {
        super(message);
    }
}
