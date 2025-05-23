package com.bloomless.core.userManagement.rest.dtos;

import lombok.Data;

@Data
public class RegisterDto {
    private String username;
    private String password;
    private String passwordAgain;
    private String email;
}
