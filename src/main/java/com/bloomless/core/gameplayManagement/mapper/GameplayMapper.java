package com.bloomless.core.gameplayManagement.mapper;

import com.bloomless.core.equipmentManagement.database.ActorEntity;
import com.bloomless.core.gameplayManagement.database.GameEnvironmentEntity;
import com.bloomless.core.gameplayManagement.database.PowerUpEntity;
import com.bloomless.core.gameplayManagement.database.RoundEntity;
import com.bloomless.core.gameplayManagement.database.RoundResultEntity;
import com.bloomless.core.gameplayManagement.rest.dtos.GameEnvironmentDto;
import com.bloomless.core.gameplayManagement.rest.dtos.PowerUpDto;
import com.bloomless.core.gameplayManagement.rest.dtos.RoundDto;
import com.bloomless.core.gameplayManagement.rest.dtos.RoundResultDto;
import com.bloomless.core.gameplayManagement.rest.resources.GameEnvironmentResource;
import com.bloomless.core.gameplayManagement.rest.resources.PowerUpResource;
import com.bloomless.core.gameplayManagement.rest.resources.RoundResource;
import com.bloomless.core.gameplayManagement.rest.resources.RoundResultResource;

import java.util.List;
import java.util.stream.Collectors;

public class GameplayMapper {

    // In Datei: src/main/java/com/bloomless/core/gameplayManagement/mapper/GameplayMapper.java

    /*public RoundResultResource convertRoundResourceToRoundResultResource(RoundResource resource) {
        RoundResultResource result = new RoundResultResource();
        result.setRoundId(resource.getId());
        result.setStage(resource.getStage());
        result.setPlayerCurrentHP(resource.getPlayerCurrentHP());
        result.setPlayerMaxHP(resource.getPlayerMaxHP());
        result.setEnemyCurrentHP(resource.getEnemyCurrentHP());
        result.setEnemyMaxHP(resource.getEnemyMaxHP());
        result.setDamageDealt(resource.getDamageDealt());
        result.setDamageTaken(resource.getDamageTaken());
        result.setDotDamageDealt(resource.getDotDamageDealt());
        result.setDotDamageTaken(resource.getDotDamageTaken());
        result.setHealingDone(resource.getHealingDone());
        result.setHealingReceived(resource.getHealingReceived());
        result.setPlayerStatusEffects(resource.getPlayerStatusEffects());
        result.setEnemyStatusEffects(resource.getEnemyStatusEffects());
        result.setPlayerAlive(resource.isPlayerAlive());
        result.setEnemyAlive(resource.isEnemyAlive());
        result.setPlayerCriticalHit(resource.isPlayerCriticalHit());
        result.setEnemyCriticalHit(resource.isEnemyCriticalHit());
        result.setXpGained(resource.getXpGained());
        result.setGoldGained(resource.getGoldGained());
        result.setPowerUpChoiceIds(resource.getPowerUpChoiceIds());
        result.setCombatLog(resource.getCombatLog());
        return result;
    }*/

    public RoundResultEntity convertRoundResultResourceToEntity(RoundResultResource resource) {
        RoundResultEntity roundResultEntity = new RoundResultEntity();
        roundResultEntity.setId(resource.getRoundId());
        roundResultEntity.setStage(resource.getStage());
        roundResultEntity.setPlayerCurrentHP(resource.getPlayerCurrentHP());
        roundResultEntity.setPlayerMaxHP(resource.getPlayerMaxHP());
        roundResultEntity.setEnemyCurrentHP(resource.getEnemyCurrentHP());
        roundResultEntity.setEnemyMaxHP(resource.getEnemyMaxHP());
        roundResultEntity.setDamageDealt(resource.getDamageDealt());
        roundResultEntity.setDamageTaken(resource.getDamageTaken());
        roundResultEntity.setDotDamageDealt(resource.getDotDamageDealt());
        roundResultEntity.setDotDamageTaken(resource.getDotDamageTaken());
        roundResultEntity.setHealingDone(resource.getHealingDone());
        roundResultEntity.setHealingReceived(resource.getHealingReceived());
        roundResultEntity.setPlayerStatusEffects(resource.getPlayerStatusEffects());
        roundResultEntity.setEnemyStatusEffects(resource.getEnemyStatusEffects());
        roundResultEntity.setPlayerAlive(resource.isPlayerAlive());
        roundResultEntity.setEnemyAlive(resource.isEnemyAlive());
        roundResultEntity.setPlayerCriticalHit(resource.isPlayerCriticalHit());
        roundResultEntity.setEnemyCriticalHit(resource.isEnemyCriticalHit());
        roundResultEntity.setXpGained(resource.getXpGained());
        roundResultEntity.setGoldGained(resource.getGoldGained());
        roundResultEntity.setPowerUpChoiceIds(resource.getPowerUpChoiceIds());
        roundResultEntity.setCombatLog(resource.getCombatLog());
        return roundResultEntity;
    }

    public RoundResultResource convertDtoToResource(RoundResultDto dto) {
        RoundResultResource resource = new RoundResultResource();
        resource.setRoundId(dto.getRoundId());
        resource.setStage(dto.getStage());
        resource.setPlayerCurrentHP(dto.getPlayerCurrentHP());
        resource.setPlayerMaxHP(dto.getPlayerMaxHP());
        resource.setEnemyCurrentHP(dto.getEnemyCurrentHP());
        resource.setEnemyMaxHP(dto.getEnemyMaxHP());
        resource.setDamageDealt(dto.getDamageDealt());
        resource.setDamageTaken(dto.getDamageTaken());
        resource.setDotDamageDealt(dto.getDotDamageDealt());
        resource.setDotDamageTaken(dto.getDotDamageTaken());
        resource.setHealingDone(dto.getHealingDone());
        resource.setHealingReceived(dto.getHealingReceived());
        resource.setPlayerStatusEffects(dto.getPlayerStatusEffects());
        resource.setEnemyStatusEffects(dto.getEnemyStatusEffects());
        resource.setPlayerAlive(dto.isPlayerAlive());
        resource.setEnemyAlive(dto.isEnemyAlive());
        resource.setPlayerCriticalHit(dto.isPlayerCriticalHit());
        resource.setEnemyCriticalHit(dto.isEnemyCriticalHit());
        resource.setXpGained(dto.getXpGained());
        resource.setGoldGained(dto.getGoldGained());
        resource.setPowerUpChoiceIds(dto.getPowerUpChoiceIds());
        resource.setCombatLog(dto.getCombatLog());
        return resource;
    }

    public RoundResultDto convertResourceToDto(RoundResultResource resource) {
        RoundResultDto dto = new RoundResultDto();
        dto.setRoundId(resource.getRoundId());
        dto.setStage(resource.getStage());
        dto.setPlayerCurrentHP(resource.getPlayerCurrentHP());
        dto.setPlayerMaxHP(resource.getPlayerMaxHP());
        dto.setEnemyCurrentHP(resource.getEnemyCurrentHP());
        dto.setEnemyMaxHP(resource.getEnemyMaxHP());
        dto.setDamageDealt(resource.getDamageDealt());
        dto.setDamageTaken(resource.getDamageTaken());
        dto.setDotDamageDealt(resource.getDotDamageDealt());
        dto.setDotDamageTaken(resource.getDotDamageTaken());
        dto.setHealingDone(resource.getHealingDone());
        dto.setHealingReceived(resource.getHealingReceived());
        dto.setPlayerStatusEffects(resource.getPlayerStatusEffects());
        dto.setEnemyStatusEffects(resource.getEnemyStatusEffects());
        dto.setPlayerAlive(resource.isPlayerAlive());
        dto.setEnemyAlive(resource.isEnemyAlive());
        dto.setPlayerCriticalHit(resource.isPlayerCriticalHit());
        dto.setEnemyCriticalHit(resource.isEnemyCriticalHit());
        dto.setXpGained(resource.getXpGained());
        dto.setGoldGained(resource.getGoldGained());
        dto.setPowerUpChoiceIds(resource.getPowerUpChoiceIds());
        dto.setCombatLog(resource.getCombatLog());
        return dto;
    }

    // --- PowerUp ---
    public PowerUpDto convertPowerUpEntityToDto(PowerUpEntity entity) {
        PowerUpDto dto = new PowerUpDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        dto.setType(entity.getType());
        return dto;
    }

    public PowerUpEntity convertPowerUpDtoToEntity(PowerUpDto dto) {
        PowerUpEntity entity = new PowerUpEntity();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setType(dto.getType());
        return entity;
    }

    public PowerUpResource convertPowerUpEntityToResource(PowerUpEntity entity) {
        PowerUpResource resource = new PowerUpResource();
        resource.setId(entity.getId());
        resource.setName(entity.getName());
        resource.setDescription(entity.getDescription());
        resource.setType(entity.getType());
        return resource;
    }

    // --- Round ---
    public RoundDto convertRoundEntityToDto(RoundEntity entity) {
        RoundDto dto = new RoundDto();
        dto.setId(entity.getId());
        dto.setStage(entity.getStage());
        dto.setPlayerId(entity.getPlayer() != null ? entity.getPlayer().getId() : null);
        dto.setEnemyId(entity.getEnemy() != null ? entity.getEnemy().getId() : null);
        dto.setPlayerAlive(entity.isPlayerAlive());
        dto.setXpGained(entity.getXpGained());
        dto.setGoldGained(entity.getGoldGained());
        dto.setPowerUpChoiceIds(entity.getPowerUpChoices() != null
                ? entity.getPowerUpChoices().stream().map(PowerUpEntity::getId).collect(Collectors.toList())
                : null);
        return dto;
    }

    // Hier werden die ActorEntity-Referenzen direkt gesetzt
    public RoundEntity convertRoundDtoToEntity(RoundDto dto, ActorEntity player, ActorEntity enemy, List<PowerUpEntity> powerUps) {
        RoundEntity entity = new RoundEntity();
        entity.setId(dto.getId());
        entity.setStage(dto.getStage());
        entity.setPlayer(player);
        entity.setEnemy(enemy);
        entity.setPlayerAlive(dto.isPlayerAlive());
        entity.setXpGained(dto.getXpGained());
        entity.setGoldGained(dto.getGoldGained());
        entity.setPowerUpChoices(powerUps);
        return entity;
    }

    public RoundResource convertRoundEntityToResource(RoundEntity entity) {
        RoundResource resource = new RoundResource();
        resource.setId(entity.getId());
        resource.setStage(entity.getStage());
        resource.setPlayerId(entity.getPlayer() != null ? entity.getPlayer().getId() : null);
        resource.setEnemyId(entity.getEnemy() != null ? entity.getEnemy().getId() : null);
        resource.setPlayerAlive(entity.isPlayerAlive());
        resource.setXpGained(entity.getXpGained());
        resource.setGoldGained(entity.getGoldGained());
        resource.setPowerUpChoiceIds(entity.getPowerUpChoices() != null
                ? entity.getPowerUpChoices().stream().map(PowerUpEntity::getId).collect(Collectors.toList())
                : null);
        return resource;
    }

    // --- GameEnvironment ---
    public GameEnvironmentDto convertGameEnvironmentEntityToDto(GameEnvironmentEntity entity) {
        GameEnvironmentDto dto = new GameEnvironmentDto();
        dto.setId(entity.getId());
        dto.setPlayerAlive(entity.isPlayerAlive());
        dto.setXpGained(entity.getXpGained());
        dto.setGoldGained(entity.getGoldGained());
        dto.setPowerUpChoiceIds(entity.getPowerUpChoices() != null
                ? entity.getPowerUpChoices().stream().map(PowerUpEntity::getId).collect(Collectors.toList())
                : null);
        return dto;
    }

    public GameEnvironmentEntity convertGameEnvironmentDtoToEntity(GameEnvironmentDto dto, List<PowerUpEntity> powerUps) {
        GameEnvironmentEntity entity = new GameEnvironmentEntity();
        entity.setId(dto.getId());
        entity.setPlayerAlive(dto.isPlayerAlive());
        entity.setXpGained(dto.getXpGained());
        entity.setGoldGained(dto.getGoldGained());
        entity.setPowerUpChoices(powerUps);
        return entity;
    }

    public GameEnvironmentResource convertGameEnvironmentEntityToResource(GameEnvironmentEntity entity) {
        GameEnvironmentResource resource = new GameEnvironmentResource();
        resource.setId(entity.getId());
        resource.setPlayerAlive(entity.isPlayerAlive());
        resource.setXpGained(entity.getXpGained());
        resource.setGoldGained(entity.getGoldGained());
        resource.setPowerUpChoiceIds(entity.getPowerUpChoices() != null
                ? entity.getPowerUpChoices().stream().map(PowerUpEntity::getId).collect(Collectors.toList())
                : null);
        return resource;
    }
}
