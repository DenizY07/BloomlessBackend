package com.bloomless.core.shopManagement.database;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "dmgItem")
public class DMGItemEntity extends ShopItemEntity {
    private int level;
    private int xp;
    private String passive;
    private int itemDMG;
    private double itemCritRate;
    private int itemCritDMG;
}
