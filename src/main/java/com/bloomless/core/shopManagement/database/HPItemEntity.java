package com.bloomless.core.shopManagement.database;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "hpItem")
public class HPItemEntity extends ShopItemEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int level;
    private int xp;
    private String passive;
    private int itemHP;
    private int itemDEF;
    private double itemRegen;
}
