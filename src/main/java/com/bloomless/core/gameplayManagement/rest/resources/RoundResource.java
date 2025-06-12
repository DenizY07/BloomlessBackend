package com.bloomless.core.gameplayManagement.rest.resources;

import lombok.Data;

import java.util.List;

@Data
public class RoundResource {
    private Long id;
    private int stage;
    private int roundCount;
    private Long playerId;
    private Long enemyId;
    private boolean playerAlive;
    private int xpGained;
    private int goldGained;
    private List<Long> powerUpChoiceIds;
}
