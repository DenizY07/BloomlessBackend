package com.bloomless.core.shopManagement.rest.dtos;

import lombok.Data;

@Data
public class DMGItemDto extends ShopItemDto{
    private int level;
    private int xp;
    private String passive;
    private int itemDMG;
    private double itemCritRate;
    private int itemCritDMG;
}
