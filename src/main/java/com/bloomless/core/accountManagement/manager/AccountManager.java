package com.bloomless.core.accountManagement.manager;

import com.bloomless.core.accountManagement.database.AccountEntity;
import com.bloomless.core.accountManagement.database.repositories.AccountRepository;
import com.bloomless.core.accountManagement.exceptions.*;
import com.bloomless.core.accountManagement.mapper.AccountMapper;
import com.bloomless.core.accountManagement.rest.dtos.AccountUpdateDto;
import com.bloomless.core.accountManagement.rest.dtos.LoginDto;
import com.bloomless.core.accountManagement.rest.dtos.RegisterDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AccountManager {
    @Autowired
    AccountRepository accountRepository;

    AccountMapper mapper = new AccountMapper();

    public AccountEntity saveToRepository(AccountEntity entity){
        return accountRepository.save(entity);
    }

    public AccountEntity findEntityInRepositoryByUsername(String username){
        return accountRepository.findByUsername(username).get();
    }

    public AccountEntity findEntityInRepositoryById(long id){
        return accountRepository.findById(id).get();
    }

    public AccountUpdateDto updateChecker(AccountUpdateDto updateDto){
        if (accountRepository.findByUsername(updateDto.getUsername()).isEmpty()){
            throw new AccountNotFound("Account wurde nicht gefunden");
        }
        return updateDto;
    }

    public RegisterDto registerChecker(RegisterDto registerDto) {

        if (accountRepository.findByUsername(registerDto.getUsername()).isPresent()) {
            throw new UsernameAlreadyExisting("Benutzername ist bereits vergeben");
        }

        if (accountRepository.findByEmail(registerDto.getEmail()).isPresent()) {
            throw new EmailAlreadyExisting("E-Mail ist bereits vergeben");
        }

        if (!registerDto.getPassword().equals(registerDto.getPasswordConfirmation())) {
            throw new PasswordNotMatching("Passwörter stimmen nicht überein");
        }

        return registerDto;
    }


    public LoginDto loginChecker(LoginDto loginDto) {
        if (accountRepository.findByUsername(loginDto.getUsername()).isEmpty()) {
            throw new UsernameNotFound("Benutzername wurde nicht gefunden");
        }

        if (!accountRepository.findByUsername(loginDto.getUsername()).get().getPassword().equals(loginDto.getPassword())) {
            throw new PasswordNotMatching("Passwort stimmt nicht überein");
        }

        return loginDto;
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
