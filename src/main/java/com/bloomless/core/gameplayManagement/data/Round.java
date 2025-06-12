package com.bloomless.core.gameplayManagement.data;

import com.bloomless.core.equipmentManagement.data.Actor;
import lombok.Data;

import java.util.List;

@Data
public class Round {
    private Long id;
    private int stage;
    private int roundCount;
    private Actor player;
    private Actor enemy;
    private boolean playeralive;
    private int xpGained;
    private int goldGained;
    private List<PowerUp> powerUpChoices;

}
