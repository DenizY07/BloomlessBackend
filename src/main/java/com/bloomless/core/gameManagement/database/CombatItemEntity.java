package com.bloomless.core.gameManagement.database;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "combatItem")
public class CombatItemEntity extends ItemEntity{
    private int level;
    private int xp;
    private String passive;
}
