package com.bloomless.core.gameplayManagement.rest.resources;

import lombok.Data;

import java.util.List;

@Data
public class GameEnvironmentResource {
    private Long id;
    private boolean playerAlive;
    private int xpGained;
    private int goldGained;
    private List<Long> powerUpChoiceIds;
}
