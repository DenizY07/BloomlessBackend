package com.bloomless.core.accountManagement.exceptions;

public class EmailAlreadyExisting extends RuntimeException {
    public EmailAlreadyExisting(String message) {
        super(message);
    }
}
