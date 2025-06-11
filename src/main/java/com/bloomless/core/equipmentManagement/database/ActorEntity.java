package com.bloomless.core.equipmentManagement.database;

import com.bloomless.core.shopManagement.database.DMGItemEntity;
import com.bloomless.core.shopManagement.database.HPItemEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "actor")
public class ActorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
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
    private DMGItemEntity dmgSlot1;

    @ManyToOne
    @JoinColumn(name = "dmg_slot2_id")
    private DMGItemEntity dmgSlot2;

    @ManyToOne
    @JoinColumn(name = "hp_slot1_id")
    private HPItemEntity hpSlot1;

    @ManyToOne
    @JoinColumn(name = "hp_slot2_id")
    private HPItemEntity hpSlot2;

    @OneToMany(mappedBy = "creature", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StatusEffectEntity> activeEffects;
}
