package com.bloomless.core.accountManagement.restController;

import com.bloomless.core.accountManagement.AccountService;
import com.bloomless.core.accountManagement.rest.dtos.RegisterDto;
import com.bloomless.core.accountManagement.rest.resources.AccountResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class LoginRESTController {
    @Autowired
    private AccountService accountService;

    @PostMapping("bloomless/user/login")
    public AccountResource login(@RequestBody RegisterDto registerDto) {
        return accountService.login(registerDto);
    }
}
