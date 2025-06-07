package com.bloomless.core.accountManagement.mapper;

import com.bloomless.core.accountManagement.data.Account;
import com.bloomless.core.accountManagement.database.AccountEntity;
import com.bloomless.core.accountManagement.rest.resources.AccountResource;
import com.bloomless.core.gameManagement.ItemService;
import com.bloomless.core.gameManagement.data.items.Item;
import com.bloomless.core.gameManagement.database.ItemEntity;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class AccountMapper {
    ItemService itemService = new ItemService();

    public AccountResource convertAccountToAccountResource(Account account){
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
