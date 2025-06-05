package com.bloomless.core.accountManagement.rest.dtos;

import lombok.Data;

@Data
public class RegisterDto {
    private String username;
    private String password;
    private String passwordConfirmation;
    private String email;
}
