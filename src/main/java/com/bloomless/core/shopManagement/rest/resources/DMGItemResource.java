package com.bloomless.core.shopManagement.rest.resources;

import lombok.Data;

@Data
public class DMGItemResource extends ShopItemResource{
    private int level;
    private int xp;
    private String passive;
    private int itemDMG;
    private double itemCritRate;
    private int itemCritDMG;
}
