package com.bloomless.core.gameplayManagement.data;

import lombok.Data;

@Data
public class StatusEffect {
    private Long id;
    private EffectType effectType;
    private int damagePerTurn;
    private int remainingTurns;
}

