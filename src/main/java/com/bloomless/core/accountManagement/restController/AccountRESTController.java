package com.bloomless.core.accountManagement.restController;

import com.bloomless.core.accountManagement.AccountService;
import com.bloomless.core.accountManagement.rest.dtos.LoginDto;
import com.bloomless.core.accountManagement.rest.dtos.RegisterDto;
import com.bloomless.core.accountManagement.rest.resources.AccountResource;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class AccountRESTController {
    @Autowired
    private AccountService accountService;

    @PostMapping(value = "bloomless/user/login")
    public AccountResource login(@RequestBody LoginDto loginDto){
        return accountService.login(loginDto);
    }

    @PostMapping("bloomless/user/register")
    public AccountResource register(@Valid @RequestBody RegisterDto registerDto) {
        return accountService.register(registerDto);
    }
}
