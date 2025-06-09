package com.bloomless.core.accountManagement.mapper;

import com.bloomless.core.accountManagement.data.Account;
import com.bloomless.core.accountManagement.database.AccountEntity;
import com.bloomless.core.accountManagement.rest.resources.AccountResource;
import com.bloomless.core.gameManagement.ItemService;
import com.bloomless.core.gameManagement.data.items.DMGItem;
import com.bloomless.core.gameManagement.data.items.HPItem;
import com.bloomless.core.gameManagement.data.items.Item;
import com.bloomless.core.gameManagement.data.items.UpgradeItem;
import com.bloomless.core.gameManagement.database.ItemEntity;
import com.bloomless.core.gameManagement.database.mapper.ItemMapper;
import com.bloomless.core.gameManagement.rest.dtos.DMGItemDto;
import com.bloomless.core.gameManagement.rest.dtos.HPItemDto;
import com.bloomless.core.gameManagement.rest.dtos.ItemDto;
import com.bloomless.core.gameManagement.rest.dtos.UpgradeItemDto;
import com.bloomless.core.gameManagement.rest.resources.ItemResource;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class AccountMapper {
    ItemMapper itemMapper = new ItemMapper();

    public AccountResource convertAccountToAccountResources(Account account){
        AccountResource accountResource = new AccountResource();
        accountResource.setId(account.getId());
        accountResource.setPassword(account.getPassword());
        accountResource.setUsername(account.getUsername());
        accountResource.setEmail(account.getEmail());
        accountResource.setHighestStage(account.getHighestStage());
        accountResource.setCurrency(accountResource.getCurrency());
        accountResource.setXp(account.getXp());
        accountResource.setProfileImage(account.getProfileImage());

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
            itemEntityList.add(itemMapper.convertItemToItemEntity(item));
        }
        result.setInventory(itemEntityList);
        return result;
    }

    public Account convertAccountEntityToAccount(AccountEntity accountEntity){
        Account result = new Account();
        result.setId(accountEntity.getId());
        result.setUsername(accountEntity.getUsername());
        result.setEmail(accountEntity.getEmail());
        result.setPassword(accountEntity.getPassword());
        result.setAccountLevel(accountEntity.getAccountLevel());
        result.setXp(accountEntity.getXp());
        result.setHighestStage(accountEntity.getHighestStage());
        result.setCurrency(accountEntity.getCurrency());
        result.setProfileImage(accountEntity.getProfileImage());

        List<Item> itemList = new ArrayList<>();
        for (ItemEntity entity : accountEntity.getInventory()){
            itemList.add(itemMapper.convertItemEntityToItem(entity));
        }
        result.setInventory(itemList);
        return result;
    }

   /* public AccountResource convertAccountToAccountResource(Account account){
        AccountResource resource = new AccountResource();
        resource.setUsername(account.getUsername());
        resource.setAccountLevel(account.getAccountLevel());
        resource.setXp(account.getXp());
        resource.setHighestStage(account.getHighestStage());
        resource.setCurrency(account.getCurrency());
        resource.setProfileImage(account.getProfileImage());

        List<ItemResource> inventory = account.getInventory().stream()
                .map(.getItemService()::convertItemToItemResource)
                .collect(Collectors.toList());

        resource.setInventory(inventory); // ✅ das füllt deine Item-Liste
        return resource;
    }*/



    public AccountResource convertAccountToAccountResource(Account account){
        AccountResource resource = new AccountResource();
        resource.setUsername(account.getUsername());
        resource.setEmail(account.getEmail());
        resource.setPassword(account.getPassword());
        resource.setId(account.getId());
        resource.setAccountLevel(account.getAccountLevel());
        resource.setXp(account.getXp());
        resource.setHighestStage(account.getHighestStage());
        resource.setCurrency(account.getCurrency());
        resource.setProfileImage(account.getProfileImage());;

        List<Item> inventory = account.getInventory();
        if (inventory != null) {
            List<ItemDto> inventoryDtos = inventory.stream()
                    .map(item -> {
                        if (item instanceof DMGItem dmg) {
                            DMGItemDto dto = new DMGItemDto();
                            dto.setName(dmg.getName());
                            dto.setLevel(dmg.getLevel());
                            dto.setXp(dmg.getXp());
                            dto.setPassive(dmg.getPassive());
                            dto.setType("dmg");
                            dto.setRarity(dmg.getRarity().name());
                            dto.setItemDMG(dmg.getItemDMG());
                            dto.setItemCritRate(dmg.getItemCritRate());
                            dto.setItemCritDMG(dmg.getItemCritDMG());
                            return dto;
                        } else if (item instanceof HPItem hp) {
                            HPItemDto dto = new HPItemDto();
                            dto.setName(hp.getName());
                            dto.setLevel(hp.getLevel());
                            dto.setXp(hp.getXp());
                            dto.setPassive(hp.getPassive());
                            dto.setType("hp");
                            dto.setRarity(hp.getRarity().name());
                            dto.setItemHP(hp.getItemHP());
                            dto.setItemDEF(hp.getItemDEF());
                            dto.setItemRegen(hp.getItemRegen());
                            return dto;
                        } else if (item instanceof UpgradeItem up) {
                            UpgradeItemDto dto = new UpgradeItemDto();
                            dto.setName(up.getName());
                            dto.setType("upgrade");
                            dto.setRarity(up.getRarity().name());
                            dto.setGivenXP(up.getGivenXP());
                            return dto;
                        } else {
                            return null;
                        }
                    })
                    .filter(dto -> dto != null)
                    .collect(Collectors.toList());

            resource.setInventory(inventoryDtos);
        }

        return resource;
    }
}
