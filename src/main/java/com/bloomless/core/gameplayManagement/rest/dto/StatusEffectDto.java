package com.bloomless.core.gameplayManagement.rest.dto;

import com.bloomless.core.gameplayManagement.data.EffectType;
import lombok.Data;

@Data
public class StatusEffectDto {
    private Long id;
    private EffectType effectType;
    private int damagePerTurn;
    private int remainingTurns;
}
