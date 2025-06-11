package com.bloomless.core.equipmentManagement.rest.dto;

import com.bloomless.core.equipmentManagement.data.EffectType;
import lombok.Data;

@Data
public class StatusEffectDto {
    private Long id;
    private EffectType effectType;
    private int damagePerTurn;
    private int remainingTurns;
}
