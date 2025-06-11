package com.bloomless.core.shopManagement.database;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "dmgItem")
public class DMGItemEntity extends ShopItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int level;
    private int xp;
    private String passive;
    private int itemDMG;
    private double itemCritRate;
    private int itemCritDMG;
}
