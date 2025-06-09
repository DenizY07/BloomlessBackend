package com.bloomless.core.gameManagement.rest.resources;

import lombok.Data;

@Data
public class HPItemResource extends ItemResource{
    private int level;
    private int xp;
    private String passive;
    private int itemHP;
    private int itemDEF;
    private double itemRegen;
}
