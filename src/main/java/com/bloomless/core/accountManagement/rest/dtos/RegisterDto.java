package com.bloomless.core.accountManagement.rest.dtos;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RegisterDto {
    private String username;
    private String password;
    private String passwordConfirmation;
    private String email;
}
