package com.bloomless.core.accountManagement.manager;

import com.bloomless.core.accountManagement.database.AccountEntity;
import com.bloomless.core.accountManagement.database.repositories.AccountRepository;
import com.bloomless.core.accountManagement.exceptions.*;
import com.bloomless.core.accountManagement.rest.dtos.RegisterDto;
import com.bloomless.core.accountManagement.rest.resources.AccountResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AccountManager {
    @Autowired
    AccountRepository accountRepository;


    public AccountResource registerChecker(RegisterDto registerDto) {
        Optional<AccountEntity> byUsername = accountRepository.findByUsername(registerDto.getUsername());
        if (byUsername.isPresent()) {
            throw new UsernameAlreadyExisting("Benutzername ist bereits vergeben");
        }

        Optional<AccountEntity> byEmail = accountRepository.findByEmail(registerDto.getEmail());
        if (byEmail.isPresent()) {
            throw new EmailAlreadyExisting("E-Mail ist bereits vergeben");
        }

        if (!registerDto.getPassword().equals(registerDto.getPasswordConfirmation())) {
            throw new PasswordNotMatching("Passwort stimmt nicht mit der Bestätigung überein");
        }

        return null;
    }


    public AccountEntity loginChecker(String username, String password) {
        Optional<AccountEntity> userOpt = accountRepository.findByUsername(username);
        if (userOpt.isEmpty()) {
            throw new UsernameNotFound("Benutzername wurde nicht gefunden");
        }
        AccountEntity user = userOpt.get();
        if (!user.getPassword().equals(password)) {
            throw new PasswordNotMatching("Passwort ist falsch");
        }
        return user;
    }

    /*public void loginChecker(RegisterDto registerDto) {
        Optional<AccountEntity> byUsername = accountRepository.findByUsername(registerDto.getUsername());
        if (!byUsername.isPresent()) {
            throw new UsernameNotFound("Benutzername wurde nicht gefunden");
        }

        Optional<AccountEntity> byPassword = accountRepository.findByPassword(registerDto.getPassword());
        if (!byPassword.isPresent()) {
            throw new PasswordNotFound("Passwort wurde nicht gefunden");
        }

    }*/


    /*public AccountResource addAccount(RegisterDto registerDto){
        if (registerDto.getPassword().equals(registerDto.getPasswordConfirmation())){
            throw new PasswordNotMatching("password does not match with the passwordConfirmation");
        }

        Account newAccount = new Account();
        newAccount.setId(-1L);
        newAccount.setUsername(registerDto.getUsername());
        newAccount.setPassword(registerDto.getPassword());
        newAccount.setEmail(registerDto.getEmail());
        newAccount.setAccountLevel(0);
        newAccount.setXp(0);
        newAccount.setHighestStage(0);
        newAccount.setProfileImage("Standard");
        newAccount.setCurrency(300);
        newAccount.setInventory(new ArrayList<>());

        AccountEntity newEntity =
        return newAccount;
    }*/


}
