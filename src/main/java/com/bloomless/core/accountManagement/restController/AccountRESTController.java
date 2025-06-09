package com.bloomless.core.accountManagement.restController;

import com.bloomless.core.accountManagement.AccountService;
import com.bloomless.core.accountManagement.rest.dtos.AccountUpdateDto;
import com.bloomless.core.accountManagement.rest.dtos.LoginDto;
import com.bloomless.core.accountManagement.rest.dtos.RegisterDto;
import com.bloomless.core.accountManagement.rest.resources.AccountResource;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class AccountRESTController {
    @Autowired
    private AccountService accountService;

    @PostMapping(value = "bloomless/user/login")
    public AccountResource login(@RequestBody LoginDto loginDto){
        return accountService.login(loginDto);
    }

    @PostMapping(value ="bloomless/user/register")
    public AccountResource register(@RequestBody RegisterDto registerDto){
        return accountService.register(registerDto);
    }

    @PutMapping("bloomless/user/update")
    public AccountResource updateAccount(@RequestBody AccountUpdateDto updateDto) {
        return accountService.updateAccount(updateDto);
    }
}
