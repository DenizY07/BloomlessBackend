package com.bloomless.core.userManagement.data.items;

import lombok.Data;

@Data
public class DMGItem extends CombatItem{
    private int itemDMG;
    private double itemCritRate;
    private int itemCritDMG;
}
