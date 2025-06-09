package com.bloomless.core.gameManagement.rest.dtos;

import lombok.Data;

@Data
public class DMGItemDto extends ItemDto{
    private int level;
    private int xp;
    private String passive;
    private int itemDMG;
    private double itemCritRate;
    private int itemCritDMG;
}
