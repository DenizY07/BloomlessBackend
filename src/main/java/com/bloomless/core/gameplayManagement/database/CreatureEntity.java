package com.bloomless.core.gameplayManagement.database;

import com.bloomless.core.shopManagement.database.ItemEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "creature")
public class CreatureEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @ManyToOne
    @JoinColumn(name = "dmg_slot1_id")
    private ItemEntity dmgSlot1;

    @ManyToOne
    @JoinColumn(name = "dmg_slot2_id")
    private ItemEntity dmgSlot2;

    @ManyToOne
    @JoinColumn(name = "hp_slot1_id")
    private ItemEntity hpSlot1;

    @ManyToOne
    @JoinColumn(name = "hp_slot2_id")
    private ItemEntity hpSlot2;

    @OneToMany(mappedBy = "creature", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StatusEffectEntity> activeEffects;
}
