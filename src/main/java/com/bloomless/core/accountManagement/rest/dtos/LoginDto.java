package com.bloomless.core.accountManagement.rest.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LoginDto {
    private String username;
    private String password;
}
