package com.bloomless.core.accountManagement;

import com.bloomless.core.accountManagement.data.Account;
import com.bloomless.core.accountManagement.database.AccountEntity;
import com.bloomless.core.accountManagement.manager.AccountManager;
import com.bloomless.core.accountManagement.mapper.AccountMapper;
import com.bloomless.core.accountManagement.rest.dtos.AccountUpdateDto;
import com.bloomless.core.accountManagement.rest.dtos.LoginDto;
import com.bloomless.core.accountManagement.rest.dtos.RegisterDto;
import com.bloomless.core.accountManagement.rest.resources.AccountResource;
import com.bloomless.core.gameManagement.data.items.Item;
import com.bloomless.core.gameManagement.database.ItemEntity;
import com.bloomless.core.gameManagement.rest.dtos.ItemDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountService {
    @Autowired
    AccountManager manager;

    AccountMapper mapper = new AccountMapper();

    public AccountResource register(RegisterDto registerDto){
        RegisterDto accDto = manager.registerChecker(registerDto);

        Account newAccount = new Account();
        newAccount.setId(-1L);
        newAccount.setUsername(accDto.getUsername());
        newAccount.setPassword(accDto.getPassword());
        newAccount.setEmail(accDto.getEmail());
        newAccount.setAccountLevel(0);
        newAccount.setXp(0);
        newAccount.setHighestStage(0);
        newAccount.setProfileImage("standard");
        newAccount.setCurrency(300);

        List<Item> list = new ArrayList<>();
        newAccount.setInventory(list);

        AccountEntity newEntity = this.manager.saveToRepository(mapper.convertAccountToAccountEntity(newAccount));
        return mapper.convertAccountToAccountResource(mapper.convertAccountEntityToAccount(newEntity));
    }

    public AccountResource login(LoginDto loginDto) {
        String username = manager.loginChecker(loginDto).getUsername();
        AccountEntity loginEntity = manager.findEntityInRepositoryByUsername(username);
        return mapper.convertAccountToAccountResource(mapper.convertAccountEntityToAccount(loginEntity));
    }

    public AccountResource updateAccount(AccountUpdateDto updateDto){
        AccountEntity updateEntity = manager.findEntityInRepositoryByUsername(manager.updateChecker(updateDto).getUsername());

        updateEntity.setAccountLevel(updateDto.getAccountLevel());
        updateEntity.setXp(updateDto.getXp());
        updateEntity.setHighestStage(updateDto.getHighestStage());
        updateEntity.setCurrency(updateDto.getCurrency());
        updateEntity.setProfileImage(updateDto.getProfileImage());

        List<ItemDto> inventory = updateDto.getInventory();
        if (inventory == null) {
            inventory = new ArrayList<>();
        }

        List<ItemEntity> itemEntityList = inventory.stream()
                .map(item -> mapper.getItemMapper().convertItemToItemEntity(mapper.getItemMapper().convertItemDtoToItem(item)))
                .collect(Collectors.toList());

        updateEntity.getInventory().clear();
        updateEntity.getInventory().addAll(itemEntityList);

        AccountEntity updatedEntity = manager.saveToRepository(updateEntity);
        return mapper.convertAccountToAccountResource(mapper.convertAccountEntityToAccount(updatedEntity));

    }





    /*public AccountResource convertAccountToAccountResource(Account account){
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
    }*/
}
