package com.bloomless.core.equipmentManagement.mapper;

import com.bloomless.core.equipmentManagement.data.Actor;
import com.bloomless.core.equipmentManagement.database.ActorEntity;
import com.bloomless.core.equipmentManagement.rest.dto.ActorDto;
import com.bloomless.core.equipmentManagement.rest.resources.ActorResource;
import com.bloomless.core.shopManagement.data.DMGItem;
import com.bloomless.core.shopManagement.data.HPItem;
import com.bloomless.core.shopManagement.mapper.ItemMapper;

import java.util.stream.Collectors;

public class ActorMapper {

    public ItemMapper itemMapper = new ItemMapper();
    public StatusEffectMapper statusEffectMapper = new StatusEffectMapper();

    /*public ActorDto convertActorToActorDto(Actor actor) {
        if (actor == null) return null;
        ActorDto dto = new ActorDto();
        dto.setId(actor.getId());
        dto.setLevel(actor.getLevel());
        dto.setName(actor.getName());
        dto.setBaseDMG(actor.getBaseDMG());
        dto.setBaseHP(actor.getBaseHP());
        dto.setBaseDEF(actor.getBaseDEF());
        dto.setBaseRegen(actor.getBaseRegen());
        dto.setBaseCritRate(actor.getBaseCritRate());
        dto.setBaseCritDMG(actor.getBaseCritDMG());
        dto.setBaseLifeSteal(actor.getBaseLifeSteal());
        dto.setBaseLuck(actor.getBaseLuck());
        dto.setSpeed(actor.getSpeed());
        dto.setBurnChance(actor.getBurnChance());
        dto.setPoisonChance(actor.getPoisonChance());
        dto.setBleedChance(actor.getBleedChance());
        dto.setDmgSlot1(itemMapper.convertItemToItemDto(actor.getDmgSlot1()));
        dto.setDmgSlot2(itemMapper.convertItemToItemDto(actor.getDmgSlot2()));
        dto.setHpSlot1(itemMapper.convertItemToItemDto(actor.getHpSlot1()));
        dto.setHpSlot2(itemMapper.convertItemToItemDto(actor.getHpSlot2()));
        if (actor.getStatusEffects() != null) {
            dto.setStatusEffects(actor.getStatusEffects().stream()
                    .map(StatusEffectMapper::convertStatusEffectToStatusEffectDto)
                    .collect(Collectors.toList()));
        }
        return dto;
    }*/

    public Actor convertActorEntityToActor(ActorEntity entity) {
        if (entity == null) return null;
        Actor actor = new Actor();
        actor.setId(entity.getId());
        actor.setName(entity.getName());
        actor.setLevel(entity.getLevel());
        actor.setBaseDMG(entity.getBaseDMG());
        actor.setBaseHP(entity.getBaseHP());
        actor.setBaseDEF(entity.getBaseDEF());
        actor.setBaseRegen(entity.getBaseRegen());
        actor.setBaseCritRate(entity.getBaseCritRate());
        actor.setBaseCritDMG(entity.getBaseCritDMG());
        actor.setBaseLifeSteal(entity.getBaseLifeSteal());
        actor.setBaseLuck(entity.getBaseLuck());
        actor.setSpeed(entity.getSpeed());
        actor.setBurnChance(entity.getBurnChance());
        actor.setPoisonChance(entity.getPoisonChance());
        actor.setBleedChance(entity.getBleedChance());
        actor.setDotResist(entity.getDotResist());
        actor.setDotBoost(entity.getDotBoost());
        actor.setDotDurationBoost(entity.getDotDurationBoost());
        actor.setDotHeal(entity.getDotHeal());
        actor.setDmgSlot1((DMGItem) itemMapper.convertItemEntityToItem(entity.getDmgSlot1()));
        actor.setDmgSlot2((DMGItem) itemMapper.convertItemEntityToItem(entity.getDmgSlot2()));
        actor.setHpSlot1((HPItem) itemMapper.convertItemEntityToItem(entity.getHpSlot1()));
        actor.setHpSlot2((HPItem) itemMapper.convertItemEntityToItem(entity.getHpSlot2()));
        if (entity.getActiveEffects() != null) {
            actor.setStatusEffects(entity.getActiveEffects().stream()
                    .map(statusEffectMapper::convertStatusEffectEntityToStatusEffect)
                    .collect(Collectors.toList()));
        }
        return actor;
    }


    public ActorResource convertActorToActorResource(Actor actor) {
        if (actor == null) return null;
        ActorResource resource = new ActorResource();
        resource.setId(actor.getId());
        resource.setLevel(actor.getLevel());
        resource.setName(actor.getName());
        resource.setBaseDMG(actor.getBaseDMG());
        resource.setBaseHP(actor.getBaseHP());
        resource.setBaseDEF(actor.getBaseDEF());
        resource.setBaseRegen(actor.getBaseRegen());
        resource.setBaseCritRate(actor.getBaseCritRate());
        resource.setBaseCritDMG(actor.getBaseCritDMG());
        resource.setBaseLifeSteal(actor.getBaseLifeSteal());
        resource.setBaseLuck(actor.getBaseLuck());
        resource.setSpeed(actor.getSpeed());
        resource.setBurnChance(actor.getBurnChance());
        resource.setPoisonChance(actor.getPoisonChance());
        resource.setBleedChance(actor.getBleedChance());
        resource.setDotResist(actor.getDotResist());
        resource.setDotBoost(actor.getDotBoost());
        resource.setDotDurationBoost(actor.getDotDurationBoost());
        resource.setDotHeal(actor.getDotHeal());

        resource.setDmgSlot1(actor.getDmgSlot1());
        resource.setDmgSlot2(actor.getDmgSlot2());
        resource.setHpSlot1(actor.getHpSlot1());
        resource.setHpSlot2(actor.getHpSlot2());

        if (actor.getStatusEffects() != null) {
            resource.setStatusEffects(actor.getStatusEffects().stream()
                    .map(StatusEffectMapper::convertStatusEffectToStatusEffectResource)
                    .collect(Collectors.toList()));
        }
        return resource;
    }

    public ActorEntity convertActorToActorEntity(Actor actor){
        if (actor == null) return null;
        ActorEntity entity = new ActorEntity();
        entity.setId(actor.getId());
        entity.setName(actor.getName());
        entity.setLevel(actor.getLevel());
        entity.setBaseDMG(actor.getBaseDMG());
        entity.setBaseHP(actor.getBaseHP());
        entity.setBaseDEF(actor.getBaseDEF());
        entity.setBaseRegen(actor.getBaseRegen());
        entity.setBaseCritRate(actor.getBaseCritRate());
        entity.setBaseCritDMG(actor.getBaseCritDMG());
        entity.setBaseLifeSteal(actor.getBaseLifeSteal());
        entity.setBaseLuck(actor.getBaseLuck());
        entity.setSpeed(actor.getSpeed());
        entity.setBurnChance(actor.getBurnChance());
        entity.setPoisonChance(actor.getPoisonChance());
        entity.setBleedChance(actor.getBleedChance());
        entity.setDotResist(actor.getDotResist());
        entity.setDotBoost(actor.getDotBoost());
        entity.setDotDurationBoost(actor.getDotDurationBoost());
        entity.setDotHeal(actor.getDotHeal());

        entity.setDmgSlot1((com.bloomless.core.shopManagement.database.DMGItemEntity) itemMapper.convertItemToItemEntity(actor.getDmgSlot1()));
        entity.setDmgSlot2((com.bloomless.core.shopManagement.database.DMGItemEntity) itemMapper.convertItemToItemEntity(actor.getDmgSlot2()));
        entity.setHpSlot1((com.bloomless.core.shopManagement.database.HPItemEntity) itemMapper.convertItemToItemEntity(actor.getHpSlot1()));
        entity.setHpSlot2((com.bloomless.core.shopManagement.database.HPItemEntity) itemMapper.convertItemToItemEntity(actor.getHpSlot2()));

        entity.setActiveEffects(
                actor.getStatusEffects() == null ? null :
                        actor.getStatusEffects().stream()
                                .map(StatusEffectMapper::convertStatusEffectToStatusEffectEntity)
                                .collect(Collectors.toList())
        );

        return entity;
    }


    public ActorResource convertActorDtoToActorResource(ActorDto dto) {
        if (dto == null) return null;
        ActorResource resource = new ActorResource();
        resource.setId(dto.getId());
        resource.setLevel(dto.getLevel());
        resource.setName(dto.getName());
        resource.setBaseDMG(dto.getBaseDMG());
        resource.setBaseHP(dto.getBaseHP());
        resource.setBaseDEF(dto.getBaseDEF());
        resource.setBaseRegen(dto.getBaseRegen());
        resource.setBaseCritRate(dto.getBaseCritRate());
        resource.setBaseCritDMG(dto.getBaseCritDMG());
        resource.setBaseLifeSteal(dto.getBaseLifeSteal());
        resource.setBaseLuck(dto.getBaseLuck());
        resource.setSpeed(dto.getSpeed());
        resource.setBurnChance(dto.getBurnChance());
        resource.setPoisonChance(dto.getPoisonChance());
        resource.setBleedChance(dto.getBleedChance());
        resource.setDotResist(dto.getDotResist());
        resource.setDotBoost(dto.getDotBoost());
        resource.setDotDurationBoost(dto.getDotDurationBoost());
        resource.setDotHeal(dto.getDotHeal());
        resource.setDmgSlot1(dto.getDmgSlot1());
        resource.setDmgSlot2(dto.getDmgSlot2());
        resource.setHpSlot1(dto.getHpSlot1());
        resource.setHpSlot2(dto.getHpSlot2());
        if (dto.getStatusEffects() != null) {
            resource.setStatusEffects(dto.getStatusEffects().stream()
                    .map(StatusEffectMapper::convertStatusEffectDtoToStatusEffectResource)
                    .collect(Collectors.toList()));
        }
        return resource;
    }
}
