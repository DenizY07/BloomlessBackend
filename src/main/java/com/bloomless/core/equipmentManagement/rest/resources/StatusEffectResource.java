package com.bloomless.core.equipmentManagement.rest.resources;

import com.bloomless.core.equipmentManagement.data.EffectType;
import lombok.Data;

@Data
public class StatusEffectResource {
    private Long id;
    private EffectType effectType;
    private int damagePerTurn;
    private int remainingTurns;
}
