package com.bloomless.common;

import com.bloomless.core.accountManagement.AccountService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {

    @Bean
    public AccountService createAccountService(){
        AccountService accountService = new AccountService();
        return accountService;
    }
}
