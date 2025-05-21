package com.smartsced.core.managment.data.items;

import lombok.Data;

@Data
public class CombatItem extends Item{
    private int level;
    private int xp;
    private boolean passive;
}
