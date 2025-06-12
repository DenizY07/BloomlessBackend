package com.bloomless.core.accountManagement;

import com.bloomless.core.accountManagement.data.Account;
import com.bloomless.core.accountManagement.database.AccountEntity;
import com.bloomless.core.accountManagement.manager.AccountManager;
import com.bloomless.core.accountManagement.mapper.AccountMapper;
import com.bloomless.core.accountManagement.rest.dtos.AccountUpdateDto;
import com.bloomless.core.accountManagement.rest.dtos.LoginDto;
import com.bloomless.core.accountManagement.rest.dtos.RegisterDto;
import com.bloomless.core.accountManagement.rest.resources.AccountResource;
import com.bloomless.core.equipmentManagement.data.Actor;
import com.bloomless.core.equipmentManagement.manager.EquipmentManager;
import com.bloomless.core.equipmentManagement.mapper.ActorMapper;
import com.bloomless.core.shopManagement.data.*;
import com.bloomless.core.shopManagement.database.ItemEntity;
import com.bloomless.core.shopManagement.database.ShopItemEntity;
import com.bloomless.core.shopManagement.database.UpgradeItemEntity;
import com.bloomless.core.shopManagement.exceptions.ItemNotFound;
import com.bloomless.core.shopManagement.manager.ItemManager;
import com.bloomless.core.shopManagement.mapper.ItemMapper;
import com.bloomless.core.shopManagement.rest.dtos.ItemDto;
import com.bloomless.core.levelSystem.LevelUp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class AccountService {
    @Autowired
    AccountManager accountManager;
    @Autowired
    EquipmentManager equipmentManager;
    @Autowired
    ItemManager itemManager;

    AccountMapper accountMapper = new AccountMapper();
    ActorMapper actorMapper = new ActorMapper();
    ItemMapper itemMapper = new ItemMapper();


    public AccountResource getAccountResourceById(Long accountId) {
        AccountEntity entity = accountManager.findEntityInRepositoryById(accountId);
        return accountMapper.convertAccountToAccountResource(accountMapper.convertAccountEntityToAccount(entity));
    }

    public AccountEntity findEntityInRepositoryById(Long id) {
        return accountManager.findEntityInRepositoryById(id);

    }



    public AccountResource removeItemFromInventory(Long accountId, Long itemId) {
        AccountEntity accountEntity = accountManager.findEntityInRepositoryById(accountId);
        List<Item> inventory = accountMapper.convertAccountEntityToAccount(accountEntity).getInventory();

        // Item mit passender ID suchen und entfernen
        boolean removed = inventory.removeIf(item -> item.getId().equals(itemId));
        if (!removed) {
            throw new ItemNotFound("Item mit ID " + itemId + " nicht im Inventar gefunden!");
        }

        // Inventar zurück in AccountEntity schreiben und speichern
        accountEntity.setInventory(
                inventory.stream()
                        .map(accountMapper.getItemMapper()::convertItemToItemEntity)
                        .collect(Collectors.toList())
        );
        accountManager.saveToRepository(accountEntity);
        return accountMapper.convertAccountToAccountResource(accountMapper.convertAccountEntityToAccount(accountEntity));
    }


    public AccountResource giveRandomUpgradeScroll(Long accountId) {
        AccountEntity accountEntity = accountManager.findEntityInRepositoryById(accountId);
        Account account = accountMapper.convertAccountEntityToAccount(accountEntity);

        // Erzeuge zufälliges UpgradeItem
        List<UpgradeItem> upgradeItems = new ArrayList<>();
        Random random = new Random();
        for (Rarity rarity : Rarity.values()) {
            UpgradeItem item = new UpgradeItem();
            item.setName(rarity.name() + " Upgrade Scroll");
            item.setType("upgrade");
            item.setRarity(rarity);
            item.setGivenXP(50 + random.nextInt(951));
            upgradeItems.add(item);
        }
        UpgradeItem randomScroll = upgradeItems.get(random.nextInt(upgradeItems.size()));

        // Konvertiere zu Entity und speichere (ID wird von DB vergeben)
        UpgradeItemEntity entity = itemMapper.convertUpgradeItemToShopItemEntity(randomScroll);
        UpgradeItemEntity savedEntity = itemManager.upgradeItemSave(entity);

        // Füge das Item dem Inventar hinzu
        account.getInventory().add(itemMapper.convertItemEntityToItem(savedEntity));
        accountManager.saveToRepository(accountMapper.convertAccountToAccountEntity(account));

        return accountMapper.convertAccountToAccountResource(accountMapper.convertAccountEntityToAccount(accountManager.findEntityInRepositoryById(accountId)));
    }

    public void levelUpItemInInventory(Long accountId, Long itemId, Long upgradeItemId) {
        AccountEntity accountEntity = accountManager.findEntityInRepositoryById(accountId);
        List<Item> inventory = accountMapper.convertAccountEntityToAccount(accountEntity).getInventory();

        Item item = inventory.stream()
                .filter(i -> i.getId().equals(itemId))
                .findFirst()
                .orElseThrow(() -> new ItemNotFound("Item nicht gefunden"));

        UpgradeItem upgradeItem = (UpgradeItem) inventory.stream()
                .filter(i -> i.getId().equals(upgradeItemId))
                .findFirst()
                .orElseThrow(() -> new ItemNotFound("UpgradeItem nicht gefunden"));

        // Item leveln
        new LevelUp().levelUpItemWithUpgrade(item, upgradeItem);

        // UpgradeItem entfernen
        inventory.removeIf(i -> i.getId().equals(upgradeItemId));

        // Inventar zurückschreiben und speichern
        accountEntity.setInventory(
                inventory.stream()
                        .map(accountMapper.getItemMapper()::convertItemToItemEntity)
                        .collect(Collectors.toList())
        );
        accountManager.saveToRepository(accountEntity);
    }

    public AccountResource register(RegisterDto registerDto){
        RegisterDto accDto = accountManager.registerChecker(registerDto);

        Account newAccount = new Account();
        newAccount.setId(-1L);
        newAccount.setUsername(accDto.getUsername());
        newAccount.setPassword(accDto.getPassword());
        newAccount.setEmail(accDto.getEmail());
        newAccount.setAccountLevel(1);
        newAccount.setXp(0);
        newAccount.setHighestStage(0);
        newAccount.setProfileImage("standard");
        newAccount.setCurrency(3000);

        List<Item> list = new ArrayList<>();
        newAccount.setInventory(list);

        AccountEntity newEntity = this.accountManager.saveToRepository(accountMapper.convertAccountToAccountEntity(newAccount));
        Actor newActor = new Actor();
        newActor.setName(newEntity.getUsername());
        newActor.setLevel(newEntity.getAccountLevel());
        newActor.setId(null);

        newActor.setBaseDMG(10);
        newActor.setBaseHP(100);
        newActor.setBaseDEF(3);
        newActor.setBaseRegen(5);
        newActor.setBaseCritRate(5);
        newActor.setBaseCritDMG(20);
        newActor.setBaseLifeSteal(0);
        newActor.setBaseLuck(2);
        newActor.setSpeed(0);
        newActor.setBurnChance(0);
        newActor.setPoisonChance(0);
        newActor.setBleedChance(0);
        newActor.setDotResist(0);
        newActor.setDotBoost(0);
        newActor.setDotDurationBoost(0);
        newActor.setDotHeal(0);


        equipmentManager.saveToRepository(actorMapper.convertActorToActorEntity(newActor));
        return accountMapper.convertAccountToAccountResource(accountMapper.convertAccountEntityToAccount(newEntity));
    }

    public AccountResource login(LoginDto loginDto) {
        String username = accountManager.loginChecker(loginDto).getUsername();
        AccountEntity loginEntity = accountManager.findEntityInRepositoryByUsername(username);
        return accountMapper.convertAccountToAccountResource(accountMapper.convertAccountEntityToAccount(loginEntity));
    }

    public void addItemToInventory(Long accountId, ShopItemEntity shopItem) {
        AccountEntity accountEntity = accountManager.findEntityInRepositoryById(accountId);
        List<ItemEntity> items = accountEntity.getInventory();
        items.add(shopItem);
        accountEntity.setInventory(items);
        accountManager.saveToRepository(accountEntity);
    }

    public AccountResource updateAccount(AccountUpdateDto updateDto){
        AccountEntity updateEntity = accountManager.findEntityInRepositoryByUsername(accountManager.updateChecker(updateDto).getUsername());

        // LevelUp-Logik
        LevelUp.LevelUpResult levelUpResult = LevelUp.addXpAndLevelUp(updateDto.getAccountLevel(), updateDto.getXp(), 0);
        updateEntity.setAccountLevel(levelUpResult.level);
        updateEntity.setXp(levelUpResult.xp);

        updateEntity.setHighestStage(updateDto.getHighestStage());
        updateEntity.setCurrency(updateDto.getCurrency());
        updateEntity.setProfileImage(updateDto.getProfileImage());

        List<ItemDto> inventory = updateDto.getInventory();
        if (inventory == null) {
            inventory = new ArrayList<>();
        }

        List<ItemEntity> itemEntityList = inventory.stream()
                .map(itemDto -> {
                    // Item aus DTO erzeugen
                    Item item = accountMapper.getItemMapper().convertItemDtoToItem(itemDto);

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
                    return accountMapper.getItemMapper().convertItemToItemEntity(item);
                })
                .collect(Collectors.toList());

        updateEntity.getInventory().clear();
        updateEntity.getInventory().addAll(itemEntityList);

        AccountEntity updatedEntity = accountManager.saveToRepository(updateEntity);


        return accountMapper.convertAccountToAccountResource(accountMapper.convertAccountEntityToAccount(updatedEntity));
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
