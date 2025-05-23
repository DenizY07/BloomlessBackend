package com.bloomless.core.userManagement.rest.dtos;

import lombok.Data;

@Data
public class LoginDto {
    private String username;
    private String password;
}
