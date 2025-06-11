package com.bloomless.core.shopManagement.mapper;

import com.bloomless.core.shopManagement.data.items.*;
import com.bloomless.core.shopManagement.database.*;
import com.bloomless.core.shopManagement.rest.dtos.*;
import com.bloomless.core.shopManagement.rest.resources.DMGItemResource;
import com.bloomless.core.shopManagement.rest.resources.HPItemResource;
import com.bloomless.core.shopManagement.rest.resources.ItemResource;
import com.bloomless.core.shopManagement.rest.resources.UpgradeItemResource;

public class ItemMapper {

    private String getPassiveOrDefault(String passive) {
        return (passive == null || passive.isBlank()) ? "keine Passive" : passive;
    }


    public ShopItem convertShopItemEntityToShopItem(ShopItemEntity entity) {
        if (entity == null) {
            return null;
        }
        if (entity instanceof DMGItemEntity dmg) {
            DMGItem item = new DMGItem();
            item.setId(dmg.getId());
            item.setName(dmg.getName());
            item.setType("dmg");
            item.setRarity(dmg.getRarity());
            item.setLevel(dmg.getLevel());
            item.setXp(dmg.getXp());
            item.setPassive(getPassiveOrDefault(dmg.getPassive()));
            item.setItemDMG(dmg.getItemDMG());
            item.setItemCritRate(dmg.getItemCritRate());
            item.setItemCritDMG(dmg.getItemCritDMG());
            return item;
        } else if (entity instanceof HPItemEntity hp) {
            HPItem item = new HPItem();
            item.setId(hp.getId());
            item.setName(hp.getName());
            item.setType("hp");
            item.setRarity(hp.getRarity());
            item.setLevel(hp.getLevel());
            item.setXp(hp.getXp());
            item.setPassive(getPassiveOrDefault(hp.getPassive()));
            item.setItemHP(hp.getItemHP());
            item.setItemDEF(hp.getItemDEF());
            item.setItemRegen(hp.getItemRegen());
            return item;
        } else {
            throw new IllegalArgumentException("Unbekannter ShopItemEntity-Typ: " + entity.getClass().getSimpleName());
        }
    }


    public Item convertItemEntityToItem(ItemEntity entity) {
        if (entity == null) {
            return null;
        }
        if (entity instanceof DMGItemEntity dmg) {
            DMGItem item = new DMGItem();
            item.setId(dmg.getId());
            item.setName(dmg.getName());
            item.setType("dmg");
            item.setRarity(dmg.getRarity());
            item.setLevel(dmg.getLevel());
            item.setXp(dmg.getXp());
            item.setPassive(getPassiveOrDefault(dmg.getPassive()));
            item.setItemDMG(dmg.getItemDMG());
            item.setItemCritRate(dmg.getItemCritRate());
            item.setItemCritDMG(dmg.getItemCritDMG());
            return item;
        } else if (entity instanceof HPItemEntity hp) {
            HPItem item = new HPItem();
            item.setId(hp.getId());
            item.setName(hp.getName());
            item.setType("hp");
            item.setRarity(hp.getRarity());
            item.setLevel(hp.getLevel());
            item.setXp(hp.getXp());
            item.setPassive(getPassiveOrDefault(hp.getPassive()));
            item.setItemHP(hp.getItemHP());
            item.setItemDEF(hp.getItemDEF());
            item.setItemRegen(hp.getItemRegen());
            return item;
        } else if (entity instanceof UpgradeItemEntity up) {
            UpgradeItem item = new UpgradeItem();
            item.setId(up.getId());
            item.setName(up.getName());
            item.setType("upgrade");
            item.setRarity(up.getRarity());
            item.setGivenXP(up.getGivenXP());
            return item;
        } else {
            throw new IllegalArgumentException("Unbekannter ItemEntity-Typ: " + entity.getClass().getSimpleName());
        }
    }

    public ItemEntity convertItemToItemEntity(Item item) {
        if (item instanceof DMGItem dmg) {
            DMGItemEntity entity = new DMGItemEntity();
            entity.setId(dmg.getId());
            entity.setName(dmg.getName());
            entity.setRarity(dmg.getRarity());
            entity.setLevel(dmg.getLevel());
            entity.setXp(dmg.getXp());
            entity.setPassive(getPassiveOrDefault(dmg.getPassive()));
            entity.setItemDMG(dmg.getItemDMG());
            entity.setItemCritRate(dmg.getItemCritRate());
            entity.setItemCritDMG(dmg.getItemCritDMG());
            return entity;
        } else if (item instanceof HPItem hp) {
            HPItemEntity entity = new HPItemEntity();
            entity.setId(hp.getId());
            entity.setName(hp.getName());
            entity.setRarity(hp.getRarity());
            entity.setLevel(hp.getLevel());
            entity.setXp(hp.getXp());
            entity.setPassive(getPassiveOrDefault(hp.getPassive()));
            entity.setItemHP(hp.getItemHP());
            entity.setItemDEF(hp.getItemDEF());
            entity.setItemRegen(hp.getItemRegen());
            return entity;
        } else if (item instanceof UpgradeItem up) {
            UpgradeItemEntity entity = new UpgradeItemEntity();
            entity.setId(up.getId());
            entity.setName(up.getName());
            entity.setRarity(up.getRarity());
            entity.setGivenXP(up.getGivenXP());
            return entity;
        } else {
            return null;
        }
    }

    public Item convertItemDtoToItem(ItemDto dto) {
        if (dto instanceof DMGItemDto dmgDto) {
            DMGItem item = new DMGItem();
            item.setId(dmgDto.getId());
            item.setName(dmgDto.getName());
            item.setType("dmg");
            item.setRarity(Rarity.valueOf(dmgDto.getRarity()));
            item.setLevel(dmgDto.getLevel());
            item.setXp(dmgDto.getXp());
            item.setPassive(getPassiveOrDefault(dmgDto.getPassive()));
            item.setItemDMG(dmgDto.getItemDMG());
            item.setItemCritRate(dmgDto.getItemCritRate());
            item.setItemCritDMG(dmgDto.getItemCritDMG());
            return item;
        } else if (dto instanceof HPItemDto hpDto) {
            HPItem item = new HPItem();
            item.setId(hpDto.getId());
            item.setName(hpDto.getName());
            item.setType("hp");
            item.setRarity(Rarity.valueOf(hpDto.getRarity()));
            item.setLevel(hpDto.getLevel());
            item.setXp(hpDto.getXp());
            item.setPassive(getPassiveOrDefault(hpDto.getPassive()));
            item.setItemHP(hpDto.getItemHP());
            item.setItemDEF(hpDto.getItemDEF());
            item.setItemRegen(hpDto.getItemRegen());
            return item;
        } else if (dto instanceof UpgradeItemDto upgradeDto) {
            UpgradeItem item = new UpgradeItem();
            item.setId(upgradeDto.getId());
            item.setName(upgradeDto.getName());
            item.setType("upgrade");
            item.setRarity(Rarity.valueOf(upgradeDto.getRarity()));
            item.setGivenXP(upgradeDto.getGivenXP());
            return item;
        } else {
            throw new IllegalArgumentException("Unknown ItemDto type: " + dto.getClass().getSimpleName());
        }
    }

    public ItemResource convertItemToItemResource(Item item) {
        if (item instanceof DMGItem dmg) {
            DMGItemResource resource = new DMGItemResource();
            resource.setId(dmg.getId());
            resource.setName(dmg.getName());
            resource.setType("dmg");
            resource.setRarity(dmg.getRarity().name());
            resource.setLevel(dmg.getLevel());
            resource.setXp(dmg.getXp());
            resource.setPassive(getPassiveOrDefault(dmg.getPassive()));
            resource.setItemDMG(dmg.getItemDMG());
            resource.setItemCritRate(dmg.getItemCritRate());
            resource.setItemCritDMG(dmg.getItemCritDMG());
            return resource;
        } else if (item instanceof HPItem hp) {
            HPItemResource resource = new HPItemResource();
            resource.setId(hp.getId());
            resource.setName(hp.getName());
            resource.setType("hp");
            resource.setRarity(hp.getRarity().name());
            resource.setLevel(hp.getLevel());
            resource.setXp(hp.getXp());
            resource.setPassive(getPassiveOrDefault(hp.getPassive()));
            resource.setItemHP(hp.getItemHP());
            resource.setItemDEF(hp.getItemDEF());
            resource.setItemRegen(hp.getItemRegen());
            return resource;
        } else if (item instanceof UpgradeItem up) {
            UpgradeItemResource resource = new UpgradeItemResource();
            resource.setId(up.getId());
            resource.setName(up.getName());
            resource.setType("upgrade");
            resource.setRarity(up.getRarity().name());
            resource.setGivenXP(up.getGivenXP());
            return resource;
        }
        return null;
    }


    public UpgradeItemEntity convertUpgradeItemToShopItemEntity(UpgradeItem item) {
        if (item == null) return null;
        UpgradeItemEntity entity = new UpgradeItemEntity();
        entity.setName(item.getName());
        entity.setType(item.getType());
        entity.setRarity(item.getRarity());
        entity.setGivenXP(item.getGivenXP());
        return entity;
    }

    public UpgradeItem convertShopItemEntityToUpgradeItem(UpgradeItemEntity entity) {
        if (entity == null) return null;
        UpgradeItem item = new UpgradeItem();
        item.setId(entity.getId());
        item.setName(entity.getName());
        item.setType(entity.getType());
        item.setRarity(entity.getRarity());
        item.setGivenXP(entity.getGivenXP());
        return item;
    }

    public static ItemDto convertItemToItemDto(Item item) {
        if (item == null) return null;
        if (item instanceof DMGItem dmg) {
            DMGItemDto dto = new DMGItemDto();
            dto.setId(dmg.getId());
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
            dto.setId(hp.getId());
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
            dto.setId(up.getId());
            dto.setType("upgrade");
            dto.setRarity(up.getRarity().name());
            dto.setGivenXP(up.getGivenXP());
            return dto;
        }
        return null;
    }

    /*public Item convertItemEntityToItem(ItemEntity entity){
        if (entity instanceof DMGItemEntity dmg) {
            DMGItem item = new DMGItem();
            item.setName(dmg.getName());
            item.setType("dmg");
            item.setRarity(dmg.getRarity());
            item.setItemDMG(dmg.getItemDMG());
            item.setItemCritRate(dmg.getItemCritRate());
            item.setItemCritDMG(dmg.getItemCritDMG());
            return item;

        } else if (entity instanceof HPItemEntity hp) {
            HPItem item = new HPItem();
            item.setName(hp.getName());
            item.setType("hp");
            item.setRarity(hp.getRarity());
            item.setItemHP(hp.getItemHP());
            item.setItemDEF(hp.getItemDEF());
            item.setItemRegen(hp.getItemRegen());
            return item;

        } else if (entity instanceof UpgradeItemEntity up) {
            UpgradeItem item = new UpgradeItem();
            item.setName(up.getName());
            item.setType("upgrade");
            item.setRarity(up.getRarity());
            item.setGivenXP(up.getGivenXP());
            return item;

        } else if (entity instanceof CombatItemEntity combat) {
            CombatItem item = new CombatItem();
            item.setName(combat.getName());
            item.setType("combat");
            item.setRarity(combat.getRarity());
            item.setLevel(combat.getLevel());
            item.setXp(combat.getXp());
            item.setPassive(combat.getPassive());
            return item;

        } else {
            return null;
        }
    }

    public ItemEntity convertItemToItemEntity(Item item){
        if (item instanceof DMGItem dmg) {
            DMGItemEntity entity = new DMGItemEntity();
            entity.setName(dmg.getName());
            entity.setRarity(dmg.getRarity());
            entity.setItemDMG(dmg.getItemDMG());
            entity.setItemCritRate(dmg.getItemCritRate());
            entity.setItemCritDMG(dmg.getItemCritDMG());
            return entity;

        } else if (item instanceof HPItem hp) {
            HPItemEntity entity = new HPItemEntity();
            entity.setName(hp.getName());
            entity.setRarity(hp.getRarity());
            entity.setItemHP(hp.getItemHP());
            entity.setItemDEF(hp.getItemDEF());
            entity.setItemRegen(hp.getItemRegen());
            return entity;

        } else if (item instanceof UpgradeItem up) {
            UpgradeItemEntity entity = new UpgradeItemEntity();
            entity.setName(up.getName());
            entity.setRarity(up.getRarity());
            entity.setGivenXP(up.getGivenXP());
            return entity;

        } else if (item instanceof CombatItem combat) {
            CombatItemEntity entity = new CombatItemEntity();
            entity.setName(combat.getName());
            entity.setRarity(combat.getRarity());
            entity.setLevel(combat.getLevel());
            entity.setXp(combat.getXp());
            entity.setPassive(combat.getPassive());
            return entity;

        } else if (item != null) {
            ItemEntity entity = new ItemEntity();
            entity.setName(item.getName());
            entity.setRarity(item.getRarity());
            return entity;
        } else {
            return null;
        }
    }

    public Item convertItemDtoToItem(ItemDto dto) {
        if (dto instanceof DMGItemDto dmgDto) {
            DMGItem item = new DMGItem();
            item.setName(dmgDto.getName());
            item.setType("dmg");
            item.setRarity(Rarity.valueOf(dmgDto.getRarity()));
            item.setItemDMG(dmgDto.getItemDMG());
            item.setItemCritRate(dmgDto.getItemCritRate());
            item.setItemCritDMG(dmgDto.getItemCritDMG());
            return item;
        } else if (dto instanceof HPItemDto hpDto) {
            HPItem item = new HPItem();
            item.setName(hpDto.getName());
            item.setType("hp");
            item.setRarity(Rarity.valueOf(hpDto.getRarity()));
            item.setItemHP(hpDto.getItemHP());
            item.setItemDEF(hpDto.getItemDEF());
            item.setItemRegen(hpDto.getItemRegen());
            return item;
        } else if (dto instanceof UpgradeItemDto upgradeDto) {
            UpgradeItem item = new UpgradeItem();
            item.setName(upgradeDto.getName());
            item.setType("upgrade");
            item.setRarity(Rarity.valueOf(upgradeDto.getRarity()));
            item.setGivenXP(upgradeDto.getGivenXP());
            return item;
        } else {
            throw new IllegalArgumentException("Unknown ItemDto type: " + dto.getClass().getSimpleName());
        }
    }

    public ItemResource convertItemToItemResource(Item item) {
        if (item instanceof DMGItem dmg) {
            DMGItemResource resource = new DMGItemResource();
            resource.setName(dmg.getName());
            resource.setType("dmg");
            resource.setRarity(dmg.getRarity().name());
            resource.setItemDMG(dmg.getItemDMG());
            resource.setItemCritRate(dmg.getItemCritRate());
            resource.setItemCritDMG(dmg.getItemCritDMG());
            return resource;
        } else if (item instanceof HPItem hp) {
            HPItemResource resource = new HPItemResource();
            resource.setName(hp.getName());
            resource.setType("hp");
            resource.setRarity(hp.getRarity().name());
            resource.setItemHP(hp.getItemHP());
            resource.setItemDEF(hp.getItemDEF());
            resource.setItemRegen(hp.getItemRegen());
            return resource;
        } else if (item instanceof UpgradeItem up) {
            UpgradeItemResource resource = new UpgradeItemResource();
            resource.setName(up.getName());
            resource.setType("upgrade");
            resource.setRarity(up.getRarity().name());
            resource.setGivenXP(up.getGivenXP());
            return resource;
        }
        return null;
    }*/

}
