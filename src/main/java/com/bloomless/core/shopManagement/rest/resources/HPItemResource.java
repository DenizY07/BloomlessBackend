package com.bloomless.core.shopManagement.rest.resources;

import lombok.Data;

@Data
public class HPItemResource extends ShopItemResource{
    private int level;
    private int xp;
    private Long id;
    private String passive;
    private int itemHP;
    private int itemDEF;
    private double itemRegen;
}
