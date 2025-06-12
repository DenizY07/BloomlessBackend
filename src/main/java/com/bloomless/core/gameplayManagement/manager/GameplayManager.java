package com.bloomless.core.gameplayManagement.manager;

import com.bloomless.core.gameplayManagement.database.GameEnvironmentEntity;
import com.bloomless.core.gameplayManagement.database.PowerUpEntity;
import com.bloomless.core.gameplayManagement.database.RoundEntity;
import com.bloomless.core.gameplayManagement.database.RoundResultEntity;
import com.bloomless.core.gameplayManagement.database.repositories.GameEnvironmentRepository;
import com.bloomless.core.gameplayManagement.database.repositories.PowerUpRepository;
import com.bloomless.core.gameplayManagement.database.repositories.RoundRepository;
import com.bloomless.core.gameplayManagement.database.repositories.RoundResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class GameplayManager {
    @Autowired
    private GameEnvironmentRepository gameEnvironmentRepository;

    @Autowired
    private RoundRepository roundRepository;

    @Autowired
    private PowerUpRepository powerUpRepository;

    @Autowired
    private RoundResultRepository roundResultRepository;

    public PowerUpEntity savePowerUpToRepository(PowerUpEntity entity) {
        return powerUpRepository.save(entity);
    }

   public List<RoundResultEntity> saveRoundResultsToRepository(List<RoundResultEntity> results){
        for(RoundResultEntity entity: results){
            roundResultRepository.save(entity);
        }
        return results;

   }

    public RoundResultEntity saveRoundResultToRepository(RoundResultEntity entity) {
        /*Optional<RoundResultEntity> existing = roundResultRepository.findById(entity.getId());
        if (existing.isPresent()) {
            RoundResultEntity managed = existing.get();
            managed.setPlayerCurrentHP(entity.getPlayerCurrentHP());
            managed.setPlayerMaxHP(entity.getPlayerMaxHP());
            managed.setEnemyCurrentHP(entity.getEnemyCurrentHP());
            managed.setEnemyMaxHP(entity.getEnemyMaxHP());

            managed.setDamageDealt(entity.getDamageDealt());
            managed.setDamageTaken(entity.getDamageTaken());
            managed.setDotDamageDealt(entity.getDotDamageDealt());
            managed.setDotDamageTaken(entity.getDotDamageTaken());
            managed.setHealingDone(entity.getHealingDone());
            managed.setHealingReceived(entity.getHealingReceived());

            managed.setPlayerStatusEffects(entity.getPlayerStatusEffects());
            managed.setEnemyStatusEffects(entity.getEnemyStatusEffects());

            managed.setPlayerAlive(entity.isPlayerAlive());
            managed.setEnemyAlive(entity.isEnemyAlive());
            managed.setPlayerCriticalHit(entity.isPlayerCriticalHit());
            managed.setEnemyCriticalHit(entity.isEnemyCriticalHit());

            managed.setXpGained(entity.getXpGained());
            managed.setGoldGained(entity.getGoldGained());
            managed.setPowerUpChoiceIds(entity.getPowerUpChoiceIds());

            managed.setCombatLog(entity.getCombatLog());

            return roundResultRepository.save(managed);
        } else {
            return roundResultRepository.save(entity);
        }
         */

        RoundResultEntity managed = new RoundResultEntity();
        managed.setPlayerCurrentHP(entity.getPlayerCurrentHP());
        managed.setPlayerMaxHP(entity.getPlayerMaxHP());
        managed.setEnemyCurrentHP(entity.getEnemyCurrentHP());
        managed.setEnemyMaxHP(entity.getEnemyMaxHP());

        managed.setDamageDealt(entity.getDamageDealt());
        managed.setDamageTaken(entity.getDamageTaken());
        managed.setDotDamageDealt(entity.getDotDamageDealt());
        managed.setDotDamageTaken(entity.getDotDamageTaken());
        managed.setHealingDone(entity.getHealingDone());
        managed.setHealingReceived(entity.getHealingReceived());

        managed.setPlayerStatusEffects(entity.getPlayerStatusEffects());
        managed.setEnemyStatusEffects(entity.getEnemyStatusEffects());

        managed.setPlayerAlive(entity.isPlayerAlive());
        managed.setEnemyAlive(entity.isEnemyAlive());
        managed.setPlayerCriticalHit(entity.isPlayerCriticalHit());
        managed.setEnemyCriticalHit(entity.isEnemyCriticalHit());

        managed.setXpGained(entity.getXpGained());
        managed.setGoldGained(entity.getGoldGained());
        managed.setPowerUpChoiceIds(entity.getPowerUpChoiceIds());

        managed.setCombatLog(entity.getCombatLog());

        return roundResultRepository.save(managed);
    }


    public PowerUpEntity findPowerUpById(Long id) {
        return powerUpRepository.findById(id).orElse(null);
    }

    public List<PowerUpEntity> getAllPowerUps() {
        List<PowerUpEntity> list = new ArrayList<>();
        powerUpRepository.findAll().forEach(list::add);
        return list;
    }

    public RoundEntity savePowerUpToRepository(RoundEntity entity) {
        return roundRepository.save(entity);
    }

    public RoundEntity findRoundById(Long id) {
        return roundRepository.findById(id).orElse(null);
    }

    public List<RoundEntity> getAllRounds() {
        List<RoundEntity> list = new ArrayList<>();
        roundRepository.findAll().forEach(list::add);
        return list;
    }

    public GameEnvironmentEntity savePowerUpToRepository(GameEnvironmentEntity entity) {
        return gameEnvironmentRepository.save(entity);
    }

    public GameEnvironmentEntity findGameEnvironmentById(Long id) {
        return gameEnvironmentRepository.findById(id).orElse(null);
    }

    public List<GameEnvironmentEntity> getAllGameEnvironments() {
        List<GameEnvironmentEntity> list = new ArrayList<>();
        gameEnvironmentRepository.findAll().forEach(list::add);
        return list;
    }

}
