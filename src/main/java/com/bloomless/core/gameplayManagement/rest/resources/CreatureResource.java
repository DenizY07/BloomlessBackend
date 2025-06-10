package com.bloomless.core.gameplayManagement.rest.resources;

import com.bloomless.core.shopManagement.data.items.Item;
import com.bloomless.core.shopManagement.rest.dtos.ItemDto;
import lombok.Data;

@Data
public class CreatureResource {
    private Long id;
    private int level;
    private int baseDMG;
    private int baseHP;
    private int baseDEF;
    private int baseRegen;
    private int baseCritRate;
    private int baseCritDMG;
    private double baseLifeSteal;
    private double baseLuck;
    private int speed;
    private double burnChance;
    private double poisonChance;
    private double bleedChance;

    private double dotResist;
    private double dotBoost;
    private double dotDurationBoost;
    private double dotHeal;

    private ItemDto dmgSlot1;
    private ItemDto dmgSlot2;
    private ItemDto hpSlot1;
    private ItemDto hpSlot2;
}
