package com.bloomless.core.accountManagement.rest.resources;

import com.bloomless.core.shopManagement.rest.dtos.ItemDto;
import lombok.Data;

import java.util.List;

@Data
public class AccountResource {
    private long id;
    private String username;
    private String password;
    private String email;
    private int accountLevel;
    private int xp;
    private int highestStage;
    private int currency;
    private String profileImage;
    private List<ItemDto> inventory;
}
