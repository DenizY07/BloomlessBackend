package com.bloomless.core.gameManagement;

import com.bloomless.core.gameManagement.data.items.*;
import com.bloomless.core.gameManagement.database.*;

public class ItemService {

    //private ItemResource

    public Item convertItemEntityToItem(ItemEntity entity){
        if (entity instanceof DMGItemEntity dmg) {
            DMGItem item = new DMGItem();
            //item.setId(dmg.getId());
            item.setRarity(dmg.getRarity());
            item.setItemDMG(dmg.getItemDMG());
            item.setItemCritRate(dmg.getItemCritRate());
            item.setItemCritDMG(dmg.getItemCritDMG());
            return item;

        } else if (entity instanceof HPItemEntity hp) {
            HPItem item = new HPItem();
            //item.setId(hp.getId());
            item.setRarity(hp.getRarity());
            item.setItemHP(hp.getItemHP());
            item.setItemDEF(hp.getItemDEF());
            item.setItemRegen(hp.getItemRegen());
            return item;

        } else if (entity instanceof UpgradeItemEntity up) {
            UpgradeItem item = new UpgradeItem();
            //item.setId(up.getId());
            item.setRarity(up.getRarity());
            item.setGivenXP(up.getGivenXP());
            return item;

        } else if (entity instanceof CombatItemEntity combat) {
            CombatItem item = new CombatItem();
            //item.setId(combat.getId());
            item.setRarity(combat.getRarity());
            item.setLevel(combat.getLevel());
            item.setXp(combat.getXp());
            item.setPassive(combat.getPassive());
            return item;

        } else if (entity != null) {
            // Fallback: ItemEntity direkt zu Item (Basis)
            Item item = new Item();
            //item.setId(entity.getId());
            item.setRarity(entity.getRarity());
            return item;
        } else {
            return null;
        }
    }

    public ItemEntity convertItemToItemEntity(Item item){
        if (item instanceof DMGItem dmg) {
            DMGItemEntity entity = new DMGItemEntity();
            //entity.setId(dmg.getId());
            entity.setRarity(dmg.getRarity());
            entity.setItemDMG(dmg.getItemDMG());
            entity.setItemCritRate(dmg.getItemCritRate());
            entity.setItemCritDMG(dmg.getItemCritDMG());
            return entity;

        } else if (item instanceof HPItem hp) {
            HPItemEntity entity = new HPItemEntity();
            //entity.setId(hp.getId());
            entity.setRarity(hp.getRarity());
            entity.setItemHP(hp.getItemHP());
            entity.setItemDEF(hp.getItemDEF());
            entity.setItemRegen(hp.getItemRegen());
            return entity;

        } else if (item instanceof UpgradeItem up) {
            UpgradeItemEntity entity = new UpgradeItemEntity();
            //entity.setId(up.getId());
            entity.setRarity(up.getRarity());
            entity.setGivenXP(up.getGivenXP());
            return entity;

        } else if (item instanceof CombatItem combat) {
            CombatItemEntity entity = new CombatItemEntity();
            //entity.setId(combat.getId());
            entity.setRarity(combat.getRarity());
            entity.setLevel(combat.getLevel());
            entity.setXp(combat.getXp());
            entity.setPassive(combat.getPassive());
            return entity;

        } else if (item != null) {
            ItemEntity entity = new ItemEntity();
            //entity.setId(item.getId());
            entity.setRarity(item.getRarity());
            return entity;
        } else {
            return null;
        }
    }
}


