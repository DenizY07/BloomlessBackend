package com.bloomless.core.gameManagement.data.items;

import lombok.Data;

@Data
public class HPItem extends CombatItem {
    private int itemHP;
    private int itemDEF;
    private double itemRegen;
}
