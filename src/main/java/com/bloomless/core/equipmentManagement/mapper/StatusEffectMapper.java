package com.bloomless.core.equipmentManagement.mapper;

import com.bloomless.core.equipmentManagement.data.StatusEffect;
import com.bloomless.core.equipmentManagement.database.StatusEffectEntity;
import com.bloomless.core.equipmentManagement.rest.dto.StatusEffectDto;
import com.bloomless.core.equipmentManagement.rest.resources.StatusEffectResource;

public class StatusEffectMapper {

    public static StatusEffect convertStatusEffectEntityToStatusEffect(StatusEffectEntity entity) {
        if (entity == null) return null;
        StatusEffect effect = new StatusEffect();
        effect.setEffectType(entity.getType());
        effect.setDamagePerTurn(entity.getDamagePerTurn());
        effect.setRemainingTurns(entity.getRemainingTurns());
        return effect;
    }

    public static StatusEffectDto convertStatusEffectToStatusEffectDto(StatusEffect effect) {
        if (effect == null) return null;
        StatusEffectDto dto = new StatusEffectDto();
        dto.setEffectType(effect.getEffectType());
        dto.setDamagePerTurn(effect.getDamagePerTurn());
        dto.setRemainingTurns(effect.getRemainingTurns());
        return dto;
    }

    public static StatusEffectEntity convertStatusEffectToStatusEffectEntity(StatusEffect effect) {
        if (effect == null) return null;
        StatusEffectEntity entity = new StatusEffectEntity();
        entity.setId(effect.getId());
        entity.setType(effect.getEffectType());
        entity.setDamagePerTurn(effect.getDamagePerTurn());
        entity.setRemainingTurns(effect.getRemainingTurns());
        return entity;
    }

    public static StatusEffectResource convertStatusEffectDtoToStatusEffectResource(StatusEffectDto dto) {
        if (dto == null) return null;
        StatusEffectResource resource = new StatusEffectResource();
        resource.setEffectType(dto.getEffectType());
        resource.setDamagePerTurn(dto.getDamagePerTurn());
        resource.setRemainingTurns(dto.getRemainingTurns());
        return resource;
    }

    public static StatusEffectResource convertStatusEffectToStatusEffectResource(StatusEffect effect) {
        if (effect == null) return null;
        StatusEffectResource resource = new StatusEffectResource();
        resource.setId(effect.getId());
        resource.setEffectType(effect.getEffectType());
        resource.setRemainingTurns(effect.getRemainingTurns());
        resource.setDamagePerTurn(effect.getDamagePerTurn());
        return resource;
    }
}
