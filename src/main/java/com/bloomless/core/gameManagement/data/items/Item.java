package com.bloomless.core.gameManagement.data.items;

import lombok.Data;

@Data
public class Item {
    private String name;
    private String type;
    private Rarity rarity;
}
