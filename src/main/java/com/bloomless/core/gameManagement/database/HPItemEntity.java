package com.bloomless.core.gameManagement.database;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "hpItem")
public class HPItemEntity extends CombatItemEntity{
    private int itemHP;
    private int itemDEF;
    private double itemRegen;
}
