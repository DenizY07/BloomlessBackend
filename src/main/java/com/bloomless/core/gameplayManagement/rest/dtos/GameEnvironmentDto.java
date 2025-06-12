package com.bloomless.core.gameplayManagement.rest.dtos;

import lombok.Data;

import java.util.List;

@Data
public class GameEnvironmentDto {
    private Long id;
    private boolean playerAlive;
    private int xpGained;
    private int goldGained;
    private List<Long> powerUpChoiceIds;
}