package com.bloomless.core.userManagement.data.items;

import lombok.Data;

@Data
public class CombatItem extends Item{
    private int level;
    private int xp;
    private boolean passive;
}
