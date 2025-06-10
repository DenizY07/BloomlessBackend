package com.bloomless.core.gameplayManagement.rest.resources;

import com.bloomless.core.gameplayManagement.data.EffectType;
import lombok.Data;

@Data
public class StatusEffectResource {
    private Long id;
    private EffectType effectType;
    private int damagePerTurn;
    private int remainingTurns;
}
