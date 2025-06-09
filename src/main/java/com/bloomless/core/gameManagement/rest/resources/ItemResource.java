package com.bloomless.core.gameManagement.rest.resources;


import com.bloomless.core.gameManagement.data.items.Rarity;
import lombok.Data;

@Data
public class ItemResource {
    private Long id;
    private String name;
    private String type;
    private String Rarity;
}
