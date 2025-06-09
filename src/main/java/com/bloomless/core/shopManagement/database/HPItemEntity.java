package com.bloomless.core.shopManagement.database;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "hpItem")
public class HPItemEntity extends ShopItemEntity{
    private int level;
    private int xp;
    private String passive;
    private int itemHP;
    private int itemDEF;
    private double itemRegen;
}
