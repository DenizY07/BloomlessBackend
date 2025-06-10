package com.bloomless.core.gameplayManagement.data;

import com.bloomless.core.shopManagement.data.items.Item;
import lombok.Data;

@Data
public class Creature {
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

    private Item dmgSlot1;
    private Item dmgSlot2;
    private Item hpSlot1;
    private Item hpSlot2;

}
