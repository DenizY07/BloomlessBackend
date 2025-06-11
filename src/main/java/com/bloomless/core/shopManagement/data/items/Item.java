package com.bloomless.core.shopManagement.data.items;

import lombok.Data;

@Data
public class Item {
    private Long id;
    private String name;
    private String type;
    private Rarity rarity;
}
