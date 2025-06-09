package com.bloomless.core.shopManagement.data.items;

import lombok.Data;

@Data
public class CombatItem extends Item {
    private int level;
    private int xp;
    private String passive;
}
