package com.bloomless.core.gameManagement.rest.resources;

import lombok.Data;

@Data
public class DMGItemResource extends ItemResource{
    private int level;
    private int xp;
    private String passive;
    private int itemDMG;
    private double itemCritRate;
    private int itemCritDMG;
}
