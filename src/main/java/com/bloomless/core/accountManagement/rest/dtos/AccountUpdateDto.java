package com.bloomless.core.accountManagement.rest.dtos;

import com.bloomless.core.gameManagement.data.items.Item;
import com.bloomless.core.gameManagement.rest.dtos.ItemDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class AccountUpdateDto {
    private String username;
    private int accountLevel;
    private int xp;
    private int highestStage;
    private int currency;
    private String profileImage;

    @JsonProperty("inventory")
    private List<ItemDto> inventory;
}
