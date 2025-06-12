package com.bloomless.core.shopManagement.data;

import lombok.Data;

@Data
public class DMGItem extends ShopItem {
    private int level;
    private int xp;
    private Long id;
    private String passive;
    private int itemDMG;
    private double itemCritRate;
    private int itemCritDMG;

}
