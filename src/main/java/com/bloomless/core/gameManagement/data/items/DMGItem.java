package com.bloomless.core.gameManagement.data.items;

import lombok.Data;

@Data
public class DMGItem extends CombatItem {
    private int itemDMG;
    private double itemCritRate;
    private int itemCritDMG;
}
