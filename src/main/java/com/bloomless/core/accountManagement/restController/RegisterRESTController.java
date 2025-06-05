package com.bloomless.core.accountManagement.restController;

import com.bloomless.core.accountManagement.AccountService;
import com.bloomless.core.accountManagement.rest.dtos.RegisterDto;
import com.bloomless.core.accountManagement.rest.resources.AccountResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class RegisterRESTController {
    @Autowired
    private AccountService accountService;

    @PostMapping("bloomless/user/register")
    public AccountResource addAccount(@RequestBody RegisterDto registerDto) {
        return accountService.register(registerDto);
    }
}
