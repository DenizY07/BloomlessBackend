package com.bloomless.core.accountManagement;

import com.bloomless.core.accountManagement.data.Account;
import com.bloomless.core.accountManagement.database.AccountEntity;
import com.bloomless.core.accountManagement.manager.AccountManager;
import com.bloomless.core.accountManagement.mapper.AccountMapper;
import com.bloomless.core.accountManagement.rest.dtos.AccountUpdateDto;
import com.bloomless.core.accountManagement.rest.dtos.LoginDto;
import com.bloomless.core.accountManagement.rest.dtos.RegisterDto;
import com.bloomless.core.accountManagement.rest.resources.AccountResource;
import com.bloomless.core.gameManagement.data.items.DMGItem;
import com.bloomless.core.gameManagement.data.items.HPItem;
import com.bloomless.core.gameManagement.data.items.Item;
import com.bloomless.core.gameManagement.database.ItemEntity;
import com.bloomless.core.gameManagement.rest.dtos.ItemDto;
import com.bloomless.core.levelSystem.LevelUp;
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

        // LevelUp-Logik
        LevelUp.LevelUpResult levelUpResult = LevelUp.addXpAndLevelUp(updateDto.getAccountLevel(), updateDto.getXp(), 0);
        updateEntity.setAccountLevel(levelUpResult.level);
        updateEntity.setXp(levelUpResult.xp);

        updateEntity.setHighestStage(updateDto.getHighestStage());
        updateEntity.setCurrency(updateDto.getCurrency());
        updateEntity.setProfileImage(updateDto.getProfileImage());

        /*OHNE LEVEL ITEM SYSTEM
        List<ItemDto> inventory = updateDto.getInventory();
        if (inventory == null) {
            inventory = new ArrayList<>();
        }

        List<ItemEntity> itemEntityList = inventory.stream()
                .map(item -> mapper.getItemMapper().convertItemToItemEntity(mapper.getItemMapper().convertItemDtoToItem(item)))
                .collect(Collectors.toList());*/

        // Item-LevelUp-Logik anwenden
        List<ItemDto> inventory = updateDto.getInventory();
        if (inventory == null) {
            inventory = new ArrayList<>();
        }

        List<ItemEntity> itemEntityList = inventory.stream()
                .map(itemDto -> {
                    // Item aus DTO erzeugen
                    Item item = mapper.getItemMapper().convertItemDtoToItem(itemDto);

                    // LevelUp-Logik für Items anwenden (z.B. nur für DMGItem/HPItem)
                    if (item instanceof DMGItem dmg) {
                        LevelUp.LevelUpResult itemLevelUp = LevelUp.addXpAndLevelUp(dmg.getLevel(), dmg.getXp(), 0);
                        dmg.setLevel(itemLevelUp.level);
                        dmg.setXp(itemLevelUp.xp);
                    } else if (item instanceof HPItem hp) {
                        LevelUp.LevelUpResult itemLevelUp = LevelUp.addXpAndLevelUp(hp.getLevel(), hp.getXp(), 0);
                        hp.setLevel(itemLevelUp.level);
                        hp.setXp(itemLevelUp.xp);
                    }
                    // ItemEntity erzeugen
                    return mapper.getItemMapper().convertItemToItemEntity(item);
                })
                .collect(Collectors.toList());

        updateEntity.getInventory().clear();
        updateEntity.getInventory().addAll(itemEntityList);

        AccountEntity updatedEntity = manager.saveToRepository(updateEntity);


        return mapper.convertAccountToAccountResource(mapper.convertAccountEntityToAccount(updatedEntity));
    }

    //UPDATE OHNE LEVEL SYSTEM
    /*public AccountResource updateAccount(AccountUpdateDto updateDto){
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

    }*/

}
