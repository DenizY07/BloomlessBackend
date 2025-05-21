package com.smartsced.core.managment.data;

import com.smartsced.core.managment.data.items.Item;
import lombok.Data;

import java.util.List;

@Data
public class Account {
    private long id;
    private String username;
    private String password;
    private String email;
    private int accountLevel;
    private int xp;
    private int highestStage;
    private String profileImage;
    private int currency;
    private List<Item> inventory;
}
