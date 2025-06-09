package com.bloomless.core.shopManagement.exceptions;

public class NotEnoughCurrency extends RuntimeException {
    public NotEnoughCurrency(String message) {
        super(message);
    }
}
