package com.bloomless.core.equipmentManagement.data;

import com.bloomless.core.shopManagement.data.DMGItem;
import com.bloomless.core.shopManagement.data.HPItem;
import lombok.Data;

import java.util.List;

@Data
public class Actor {
    private Long id;
    private String name;
    private int level;
    private int baseDMG;
    private int baseHP;
    private int currentHP;
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

    private DMGItem dmgSlot1;
    private DMGItem dmgSlot2;
    private HPItem hpSlot1;
    private HPItem hpSlot2;

    private List<StatusEffect> statusEffects;

}
