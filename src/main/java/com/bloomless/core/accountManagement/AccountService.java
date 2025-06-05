package com.bloomless.core.accountManagement;

import com.bloomless.core.accountManagement.data.Account;
import com.bloomless.core.accountManagement.database.AccountEntity;
import com.bloomless.core.accountManagement.database.repositories.AccountRepository;
import com.bloomless.core.accountManagement.exceptions.PasswordNotMatching;
import com.bloomless.core.accountManagement.manager.AccountManager;
import com.bloomless.core.accountManagement.rest.dtos.RegisterDto;
import com.bloomless.core.accountManagement.rest.resources.AccountResource;
import com.bloomless.core.gameManagement.ItemService;
import com.bloomless.core.gameManagement.data.items.Item;
import com.bloomless.core.gameManagement.database.ItemEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountService {
    ItemService itemService = new ItemService();
    @Autowired
    AccountManager manager;
    @Autowired
    AccountRepository accountRepository;

    public AccountResource register(RegisterDto registerDto){ //addAccount
        manager.registerChecker(registerDto);

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

        AccountEntity newEntity = this.accountRepository.save(convertAccountToAccountEntity(newAccount));
        return convertAccountToAccountResource(convertAccountEntityToAccount(newEntity));
    }

    public AccountResource login(RegisterDto registerDto) {
        AccountEntity user = manager.loginChecker(registerDto.getUsername(), registerDto.getPassword());
        AccountResource resource = new AccountResource();
        resource.setUsername(user.getUsername());
        resource.setPassword(null); // Niemals Passwort zurückgeben!
        return resource;
    }

    /*public AccountResource login(RegisterDto registerDto){ //addAccount
        manager.loginChecker(registerDto);

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

        AccountEntity newEntity = this.accountRepository.save(convertAccountToAccountEntity(newAccount));
        return convertAccountToAccountResource(convertAccountEntityToAccount(newEntity));
    }*/


    private AccountResource convertAccountToAccountResource(Account account){
        AccountResource accountResource = new AccountResource();
        accountResource.setPassword(account.getPassword());
        accountResource.setUsername(account.getUsername());
        return accountResource;
    }

    public AccountEntity convertAccountToAccountEntity(Account account){
        AccountEntity result = new AccountEntity();

        if (account.getId() != -1){
            result.setId(account.getId());
        }

        result.setUsername(account.getUsername());
        result.setPassword(account.getPassword());
        result.setEmail(account.getEmail());
        result.setAccountLevel(account.getAccountLevel());
        result.setXp(account.getXp());
        result.setHighestStage(account.getHighestStage());
        result.setCurrency(account.getCurrency());
        result.setProfileImage(account.getProfileImage());

        List<ItemEntity> itemEntityList = new ArrayList<>();
        for(Item item: account.getInventory()){
            itemEntityList.add(itemService.convertItemToItemEntity(item));
        }
        result.setInventory(itemEntityList);
        return result;
    }

    public Account convertAccountEntityToAccount(AccountEntity accountEntity){
        Account result = new Account();
        result.setId(accountEntity.getId());
        result.setUsername(accountEntity.getUsername());
        result.setEmail(accountEntity.getEmail());
        result.setAccountLevel(accountEntity.getAccountLevel());
        result.setXp(accountEntity.getXp());
        result.setHighestStage(accountEntity.getHighestStage());
        result.setCurrency(accountEntity.getCurrency());
        result.setProfileImage(accountEntity.getProfileImage());

        List<Item> itemList = new ArrayList<>();
        for (ItemEntity entity : accountEntity.getInventory()){
            itemList.add(itemService.convertItemEntityToItem(entity));
        }
        result.setInventory(itemList);
        return result;
    }
}
