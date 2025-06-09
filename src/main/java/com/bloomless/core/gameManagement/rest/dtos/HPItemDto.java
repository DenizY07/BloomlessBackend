package com.bloomless.core.gameManagement.rest.dtos;

import lombok.Data;

@Data
public class HPItemDto extends ItemDto{
    private int level;
    private int xp;
    private String passive;
    private int itemHP;
    private int itemDEF;
    private double itemRegen;
}
