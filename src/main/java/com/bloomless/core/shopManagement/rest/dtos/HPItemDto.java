package com.bloomless.core.shopManagement.rest.dtos;

import lombok.Data;

@Data
public class HPItemDto extends ShopItemDto{
    private int level;
    private int xp;
    private Long id;
    private String passive;
    private int itemHP;
    private int itemDEF;
    private double itemRegen;
}
