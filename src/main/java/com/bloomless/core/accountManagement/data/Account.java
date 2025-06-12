package com.bloomless.core.accountManagement.data;

import com.bloomless.core.shopManagement.data.Item;
import lombok.Data;


import java.util.List;

@Data
public class Account {
    private Long id;
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
