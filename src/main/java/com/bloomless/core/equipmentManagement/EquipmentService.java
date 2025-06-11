package com.bloomless.core.equipmentManagement;

import com.bloomless.core.equipmentManagement.database.ActorEntity;
import com.bloomless.core.equipmentManagement.exceptions.UnknownItemSlotNumber;
import com.bloomless.core.equipmentManagement.manager.EquipmentManager;
import com.bloomless.core.equipmentManagement.mapper.ActorMapper;
import com.bloomless.core.equipmentManagement.rest.dto.ActorDto;
import com.bloomless.core.equipmentManagement.rest.resources.ActorResource;
import com.bloomless.core.levelSystem.LevelUp;
import com.bloomless.core.shopManagement.data.items.*;
import com.bloomless.core.shopManagement.manager.ItemManager;
import com.bloomless.core.shopManagement.mapper.ItemMapper;
import com.bloomless.core.shopManagement.rest.resources.ItemResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class EquipmentService {

    @Autowired
    EquipmentManager equipmentManager;

    @Autowired
    ItemManager itemManager;

    ActorMapper actorMapper = new ActorMapper();
    ItemMapper itemMapper = new ItemMapper();

    public List<ActorResource> getAllActors() {
        List<ActorEntity> entities = equipmentManager.getAllPlayer();
        return entities.stream()
                .map(entity -> actorMapper.convertActorToActorResource(actorMapper.convertActorEntityToActor(entity)))
                .collect(Collectors.toList());
    }

    public List<UpgradeItem> createAllUpgradeItemsWithRandomXP() {
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
        return upgradeItems;
    }



    public ItemResource levelUpItemWithUpgrade(Long itemId, Long upgradeItemId) {
        Item item = itemMapper.convertItemEntityToItem(itemManager.findItemInRepositoryById(itemId));
        UpgradeItem upgradeItem = (UpgradeItem) itemMapper.convertItemEntityToItem(itemManager.findItemInRepositoryById(upgradeItemId));

        if (item == null || upgradeItem == null) {
            throw new IllegalArgumentException("Item oder UpgradeItem ist null");
        }
        if (!item.getRarity().equals(upgradeItem.getRarity())) {
            throw new IllegalArgumentException("UpgradeItem und Item haben nicht die gleiche Rarity!");
        }

        if (item instanceof DMGItem dmg) {
            int newXp = dmg.getXp() + upgradeItem.getGivenXP();
            LevelUp.LevelUpResult result = LevelUp.addXpAndLevelUp(dmg.getLevel(), newXp, 0);
            dmg.setLevel(result.level);
            dmg.setXp(result.xp);
            return itemMapper.convertItemToItemResource(dmg);
        } else if (item instanceof HPItem hp) {
            int newXp = hp.getXp() + upgradeItem.getGivenXP();
            LevelUp.LevelUpResult result = LevelUp.addXpAndLevelUp(hp.getLevel(), newXp, 0);
            hp.setLevel(result.level);
            hp.setXp(result.xp);
            return itemMapper.convertItemToItemResource(hp);
        } else {
            throw new IllegalArgumentException("Item-Typ nicht unterstützt!");
        }
    }

    public ActorResource unequipItemWithId(Long actorId, Long itemId, String slotNumber){
        ActorEntity actor = equipmentManager.findActorInRepositoryById(actorId);

        switch (slotNumber) {
            case "hp1" -> {
                if (actor.getHpSlot1() == null)
                    throw new IllegalStateException("Kein Item in Slot hp1 ausgerüstet!");
                if (!actor.getHpSlot1().getId().equals(itemId))
                    throw new IllegalArgumentException("Falsches Item im Slot hp1!");
                actor.setHpSlot1(null);
            }
            case "hp2" -> {
                if (actor.getHpSlot2() == null)
                    throw new IllegalStateException("Kein Item in Slot hp2 ausgerüstet!");
                if (!actor.getHpSlot2().getId().equals(itemId))
                    throw new IllegalArgumentException("Falsches Item im Slot hp2!");
                actor.setHpSlot2(null);
            }
            case "dmg1" -> {
                if (actor.getDmgSlot1() == null)
                    throw new IllegalStateException("Kein Item in Slot dmg1 ausgerüstet!");
                if (!actor.getDmgSlot1().getId().equals(itemId))
                    throw new IllegalArgumentException("Falsches Item im Slot dmg1!");
                actor.setDmgSlot1(null);
            }
            case "dmg2" -> {
                if (actor.getDmgSlot2() == null)
                    throw new IllegalStateException("Kein Item in Slot dmg2 ausgerüstet!");
                if (!actor.getDmgSlot2().getId().equals(itemId))
                    throw new IllegalArgumentException("Falsches Item im Slot dmg2!");
                actor.setDmgSlot2(null);
            }
            default -> throw new UnknownItemSlotNumber("Unbekannter Slot: " + slotNumber);
        }

        // Werte neu berechnen
        /*int level = actor.getLevel();
        int baseHp = 100 + (level - 1) * 20;
        int baseDmg = 10 + (level - 1) * 3;
        int bonusHp = 0;
        int bonusDmg = 0;
        if (actor.getHpSlot1() != null) bonusHp += actor.getHpSlot1().getItemHP();
        if (actor.getHpSlot2() != null) bonusHp += actor.getHpSlot2().getItemHP();
        if (actor.getDmgSlot1() != null) bonusDmg += actor.getDmgSlot1().getItemDMG();
        if (actor.getDmgSlot2() != null) bonusDmg += actor.getDmgSlot2().getItemDMG();*/

        int level = actor.getLevel();
        int baseHp = 100 + (level - 1) * 20;
        int baseDmg = 10 + (level - 1) * 3;
        int bonusHp = 0;
        int bonusDmg = 0;
        int bonusCritDmg = 10;
        int bonusCritRate = 5;
        int bonusDef = 3;
        int bonusHpRegen = 2;

        if (actor.getHpSlot1() != null) {
            bonusHp += actor.getHpSlot1().getItemHP();
            bonusDef += actor.getHpSlot1().getItemDEF();
            bonusHpRegen += actor.getHpSlot1().getItemRegen();
        }
        if (actor.getHpSlot2() != null) {
            bonusHp += actor.getHpSlot2().getItemHP();
            bonusDef += actor.getHpSlot2().getItemDEF();
            bonusHpRegen += actor.getHpSlot2().getItemHP();
        }
        if (actor.getDmgSlot1() != null) {
            bonusDmg += actor.getDmgSlot1().getItemDMG();
            bonusCritDmg += actor.getDmgSlot1().getItemCritDMG();
            bonusCritRate += actor.getDmgSlot1().getItemCritRate();

        }
        if (actor.getDmgSlot2() != null) {
            bonusDmg += actor.getDmgSlot2().getItemDMG();
            bonusCritDmg += actor.getDmgSlot2().getItemCritDMG();
            bonusCritRate += actor.getDmgSlot2().getItemCritRate();

        }

        actor.setBaseHP(baseHp + bonusHp);
        actor.setBaseDEF(bonusDef+bonusDef);
        actor.setBaseRegen(bonusHpRegen+bonusDef);

        actor.setBaseDMG(baseDmg + bonusDmg);
        actor.setBaseCritDMG(bonusCritDmg+ bonusCritDmg);
        actor.setBaseCritRate(bonusCritRate + bonusCritRate);

        equipmentManager.saveToRepository(actor);

        return actorMapper.convertActorToActorResource(actorMapper.convertActorEntityToActor(actor));

    }

    public ActorResource equipItemWithId(Long actorId, Long itemId, String slotNumber) {
        ActorEntity actor = equipmentManager.findActorInRepositoryById(actorId);

        switch (slotNumber) {
            case "hp1" -> actor.setHpSlot1(itemManager.findHPItemEntityById(itemId));
            case "hp2" -> actor.setHpSlot2(itemManager.findHPItemEntityById(itemId));
            case "dmg1" -> actor.setDmgSlot1(itemManager.findDMGItemEntityById(itemId));
            case "dmg2" -> actor.setDmgSlot2(itemManager.findDMGItemEntityById(itemId));
            default -> throw new IllegalArgumentException("Unbekannter Slot: " + slotNumber);
        }

        // Werte neu berechnen (wie in updateActor)
        /*int level = actor.getLevel();
        int baseHp = 100 + (level - 1) * 20;
        int baseDmg = 10 + (level - 1) * 3;
        int bonusHp = 0;
        int bonusDmg = 0;
        if (actor.getHpSlot1() != null) bonusHp += actor.getHpSlot1().getItemHP();
        if (actor.getHpSlot2() != null) bonusHp += actor.getHpSlot2().getItemHP();
        if (actor.getDmgSlot1() != null) bonusDmg += actor.getDmgSlot1().getItemDMG();
        if (actor.getDmgSlot2() != null) bonusDmg += actor.getDmgSlot2().getItemDMG();

        actor.setBaseHP(baseHp + bonusHp);
        actor.setBaseDMG(baseDmg + bonusDmg);

        gameplayManager.saveToRepository(actor);*/

        int level = actor.getLevel();
        int baseHp = 100 + (level - 1) * 20;
        int baseDmg = 10 + (level - 1) * 3;
        int bonusHp = 0;
        int bonusDmg = 0;
        int bonusCritDmg = 10;
        int bonusCritRate = 5;
        int bonusDef = 3;
        int bonusHpRegen = 2;

        if (actor.getHpSlot1() != null) {
            bonusHp += actor.getHpSlot1().getItemHP();
            bonusDef += actor.getHpSlot1().getItemDEF();
            bonusHpRegen += actor.getHpSlot1().getItemRegen();
        }
        if (actor.getHpSlot2() != null) {
            bonusHp += actor.getHpSlot2().getItemHP();
            bonusDef += actor.getHpSlot2().getItemDEF();
            bonusHpRegen += actor.getHpSlot2().getItemHP();
        }
        if (actor.getDmgSlot1() != null) {
            bonusDmg += actor.getDmgSlot1().getItemDMG();
            bonusCritDmg += actor.getDmgSlot1().getItemCritDMG();
            bonusCritRate += actor.getDmgSlot1().getItemCritRate();

        }
        if (actor.getDmgSlot2() != null) {
            bonusDmg += actor.getDmgSlot2().getItemDMG();
            bonusCritDmg += actor.getDmgSlot2().getItemCritDMG();
            bonusCritRate += actor.getDmgSlot2().getItemCritRate();

        }

        actor.setBaseHP(baseHp + bonusHp);
        actor.setBaseDEF(bonusDef+bonusDef);
        actor.setBaseRegen(bonusHpRegen+bonusDef);

        actor.setBaseDMG(baseDmg + bonusDmg);
        actor.setBaseCritDMG(bonusCritDmg+ bonusCritDmg);
        actor.setBaseCritRate(bonusCritRate + bonusCritRate);

        equipmentManager.saveToRepository(actor);

        return actorMapper.convertActorToActorResource(actorMapper.convertActorEntityToActor(actor));
    }

    public ActorResource equipItemWithName(Long actorId, String itemName,String slotNumber) {
        ActorEntity actor = equipmentManager.findActorInRepositoryById(actorId);

        switch (slotNumber) {
            case "hp1" -> actor.setHpSlot1(itemManager.findHPItemEntityByName(itemName));
            case "hp2" -> actor.setHpSlot2(itemManager.findHPItemEntityByName(itemName));
            case "dmg1" -> actor.setDmgSlot1(itemManager.findDMGItemEntityByName(itemName));
            case "dmg2" -> actor.setDmgSlot2(itemManager.findDMGItemEntityByName(itemName));
            default -> throw new UnknownItemSlotNumber("Unbekannter Slot: " + slotNumber);
        }

        // Werte neu berechnen (wie in updateActor)
        int level = actor.getLevel();
        int baseHp = 100 + (level - 1) * 20;
        int baseDmg = 10 + (level - 1) * 3;
        int bonusHp = 0;
        int bonusDmg = 0;
        if (actor.getHpSlot1() != null) bonusHp += actor.getHpSlot1().getItemHP();
        if (actor.getHpSlot2() != null) bonusHp += actor.getHpSlot2().getItemHP();
        if (actor.getDmgSlot1() != null) bonusDmg += actor.getDmgSlot1().getItemDMG();
        if (actor.getDmgSlot2() != null) bonusDmg += actor.getDmgSlot2().getItemDMG();

        actor.setBaseHP(baseHp + bonusHp);
        actor.setBaseDMG(baseDmg + bonusDmg);

        equipmentManager.saveToRepository(actor);

        return actorMapper.convertActorToActorResource(actorMapper.convertActorEntityToActor(actor));
    }



    //Paar stats fehlen, Dot fehlt komplett
    public ActorResource updatePlayer(ActorDto actorDto){
        ActorEntity actorEntity = equipmentManager.findActorInRepositoryById(actorDto.getId());

        if (actorDto.getHpSlot1() != null)
            actorEntity.setHpSlot1(itemManager.findHPItemEntityById(actorDto.getHpSlot1().getId()));
        if (actorDto.getHpSlot2() != null)
            actorEntity.setHpSlot2(itemManager.findHPItemEntityById(actorDto.getHpSlot2().getId()));
        if (actorDto.getDmgSlot1() != null)
            actorEntity.setDmgSlot1(itemManager.findDMGItemEntityById(actorDto.getDmgSlot1().getId()));
        if (actorDto.getDmgSlot2() != null)
            actorEntity.setDmgSlot2(itemManager.findDMGItemEntityById(actorDto.getDmgSlot2().getId()));

        int level = actorDto.getLevel();
        int baseHp = 100 + (level - 1) * 20;
        int baseDmg = 10 + (level - 1) * 3;
        int bonusHp = 0;
        int bonusDmg = 0;
        if (actorDto.getHpSlot1() != null) bonusHp += actorDto.getHpSlot1().getItemHP();
        if (actorDto.getHpSlot2() != null) bonusHp += actorDto.getHpSlot2().getItemHP();
        if (actorDto.getDmgSlot1() != null) bonusDmg += actorDto.getDmgSlot1().getItemDMG();
        if (actorDto.getDmgSlot2() != null) bonusDmg += actorDto.getDmgSlot2().getItemDMG();

        actorEntity.setBaseHP(baseHp + bonusHp);
        actorEntity.setBaseDMG(baseDmg + bonusDmg);
        actorEntity.setLevel(level);

        equipmentManager.saveToRepository(actorEntity);

        return actorMapper.convertActorToActorResource(actorMapper.convertActorEntityToActor(actorEntity));
    }

}
