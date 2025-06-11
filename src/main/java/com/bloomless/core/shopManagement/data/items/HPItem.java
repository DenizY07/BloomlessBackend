package com.bloomless.core.shopManagement.data.items;

import lombok.Data;

@Data
public class HPItem extends ShopItem {
    private int level;
    private int xp;
    private Long id;
    private String passive;
    private int itemHP;
    private int itemDEF;
    private double itemRegen;
}
