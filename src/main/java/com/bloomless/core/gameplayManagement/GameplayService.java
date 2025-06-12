package com.bloomless.core.gameplayManagement;

import com.bloomless.core.accountManagement.database.AccountEntity;
import com.bloomless.core.accountManagement.manager.AccountManager;
import com.bloomless.core.equipmentManagement.data.EffectType;
import com.bloomless.core.equipmentManagement.data.StatusEffect;
import com.bloomless.core.equipmentManagement.database.ActorEntity;
import com.bloomless.core.equipmentManagement.manager.EquipmentManager;
import com.bloomless.core.equipmentManagement.mapper.ActorMapper;
import com.bloomless.core.equipmentManagement.mapper.StatusEffectMapper;
import com.bloomless.core.gameplayManagement.data.PowerUpTyp;
import com.bloomless.core.gameplayManagement.database.PowerUpEntity;
import com.bloomless.core.gameplayManagement.database.RoundEntity;
import com.bloomless.core.gameplayManagement.manager.GameplayManager;
import com.bloomless.core.gameplayManagement.mapper.GameplayMapper;
import com.bloomless.core.gameplayManagement.rest.resources.RoundResultResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class GameplayService {

    @Autowired
    private EquipmentManager equipmentManager;
    @Autowired
    private GameplayManager gameplayManager;
    @Autowired
    private AccountManager accountManager;
    private GameplayMapper gameplayMapper = new GameplayMapper();
    private ActorMapper actorMapper = new ActorMapper();
    private StatusEffectMapper statusEffectMapper = new StatusEffectMapper();

    private int currentStage = 1;
    private List<PowerUpEntity> currentPowerUpChoices = new ArrayList<>();
    private PowerUpEntity chosenPowerUp = null;
    private ActorEntity currentEnemy = null;

    // 1. Startet eine neue Stage und gibt 3 PowerUps zur Auswahl zurück
    public List<PowerUpEntity> startNewStage(Long playerId) {
        currentPowerUpChoices = getRandomPowerUps();
        chosenPowerUp = null;
        currentEnemy = generateRandomEnemy(currentStage);
        return currentPowerUpChoices;
    }




    public RoundResultResource choosePowerUpAndStartFight(Long playerId, int powerUpIndex) {
        if (powerUpIndex < 0 || powerUpIndex >= currentPowerUpChoices.size()) {
            throw new IllegalArgumentException("Ungültiges PowerUp gewählt");
        }
        chosenPowerUp = currentPowerUpChoices.get(powerUpIndex);

        ActorEntity player = equipmentManager.findActorInRepositoryById(playerId);
        applyPowerUpToPlayer(player, chosenPowerUp);

        player = equipmentManager.saveToRepository(player);

        // Gegner speichern
        currentEnemy = equipmentManager.saveToRepository(currentEnemy);

        // Runde anlegen
        RoundEntity round = new RoundEntity();
        round.setPlayer(player);
        round.setEnemy(currentEnemy);
        round.setPowerUpChoices(List.of(chosenPowerUp));
        round.setStage(currentStage);
        round.setPlayerAlive(true);

        gameplayManager.savePowerUpToRepository(round);

        // RoundResultResource befüllen
        RoundResultResource result = new RoundResultResource();
        result.setRoundId(round.getId());
        result.setPlayer(actorMapper.convertActorEntityToActor(player));
        result.setEnemy(actorMapper.convertActorEntityToActor(currentEnemy));
        result.setStage(currentStage);
        result.setPlayerAlive(true);
        result.setEnemyAlive(true);
        result.setPowerUpChoiceIds(List.of(chosenPowerUp.getId().intValue()));
        // Weitere Felder nach Bedarf setzen

        return result;
    }



    public List<RoundResultResource> fightRound(Long roundId) {
        RoundEntity round = gameplayManager.findRoundById(roundId);
        ActorEntity player = round.getPlayer();
        ActorEntity enemy = round.getEnemy();

        // HP nur zu Kampfbeginn setzen!
        if (round.getRoundCount() == 0) {
            player.setCurrentHP(player.getBaseHP());
            enemy.setCurrentHP(enemy.getBaseHP());
        }
        equipmentManager.saveToRepository(player);
        equipmentManager.saveToRepository(enemy);

        int playerCurrentHP = player.getCurrentHP();
        int enemyCurrentHP = enemy.getCurrentHP();
        int playerMaxHP = player.getBaseHP();
        int enemyMaxHP = enemy.getBaseHP();

        List<StatusEffect> playerEff = player.getActiveEffects() != null
                ? player.getActiveEffects().stream().map(statusEffectMapper::convertStatusEffectEntityToStatusEffect).collect(Collectors.toList())
                : new ArrayList<>();
        List<StatusEffect> enemyEff = enemy.getActiveEffects() != null
                ? enemy.getActiveEffects().stream().map(statusEffectMapper::convertStatusEffectEntityToStatusEffect).collect(Collectors.toList())
                : new ArrayList<>();

        List<RoundResultResource> roundResults = new ArrayList<>();
        boolean playerAlive = true;
        boolean enemyAlive = true;
        int roundCount = round.getRoundCount() == 0 ? 1 : round.getRoundCount();

        while (playerCurrentHP > 0 && enemyCurrentHP > 0) {
            List<String> combatLog = new ArrayList<>();
            combatLog.add("Runde " + roundCount + " startet.");

            // DoT-Schaden berechnen
            int dotToPlayer = enemyEff.stream()
                    .filter(e -> e.getEffectType() == EffectType.BURN || e.getEffectType() == EffectType.POISON)
                    .mapToInt(StatusEffect::getDamagePerTurn).sum();
            int dotToEnemy = playerEff.stream()
                    .filter(e -> e.getEffectType() == EffectType.BURN || e.getEffectType() == EffectType.POISON)
                    .mapToInt(StatusEffect::getDamagePerTurn).sum();

            // Heilung berechnen
            int playerRegen = Math.max(0, player.getBaseRegen());
            int enemyRegen = Math.max(0, enemy.getBaseRegen());

            // Wer greift zuerst an?
            boolean playerFirst = player.getSpeed() >= enemy.getSpeed();

            for (int i = 0; i < 2; i++) {
                if ((i == 0 && playerFirst) || (i == 1 && !playerFirst)) {
                    // Player greift an
                    int dmg = Math.max(0, player.getBaseDMG() - enemy.getBaseDEF());
                    boolean crit = Math.random() < (player.getBaseCritRate() + player.getBaseLuck()) / 100.0;
                    if (crit) dmg += dmg * player.getBaseCritDMG() / 100;
                    enemyCurrentHP -= dmg;
                    combatLog.add("Player greift an und macht " + dmg + " Schaden" + (crit ? " (Kritisch!)" : "") + ".");
                    if (enemyCurrentHP <= 0) {
                        enemyCurrentHP = 0;
                        enemyAlive = false;
                        combatLog.add("Enemy wurde besiegt!");
                        break;
                    }
                } else {
                    // Enemy greift an
                    int dmg = Math.max(0, enemy.getBaseDMG() - player.getBaseDEF());
                    boolean crit = Math.random() < (enemy.getBaseCritRate() + enemy.getBaseLuck()) / 100.0;
                    if (crit) dmg += dmg * enemy.getBaseCritDMG() / 100;
                    playerCurrentHP -= dmg;
                    combatLog.add("Enemy greift an und macht " + dmg + " Schaden" + (crit ? " (Kritisch!)" : "") + ".");
                    if (playerCurrentHP <= 0) {
                        playerCurrentHP = 0;
                        playerAlive = false;
                        combatLog.add("Player wurde besiegt!");
                        break;
                    }
                }
            }
            if (!playerAlive || !enemyAlive) break;

            // DoT anwenden
            if (dotToPlayer > 0) {
                playerCurrentHP -= dotToPlayer;
                combatLog.add("Player erleidet " + dotToPlayer + " DoT-Schaden.");
                if (playerCurrentHP <= 0) {
                    playerCurrentHP = 0;
                    playerAlive = false;
                    combatLog.add("Player wurde durch DoT besiegt!");
                    break;
                }
            }
            if (dotToEnemy > 0) {
                enemyCurrentHP -= dotToEnemy;
                combatLog.add("Enemy erleidet " + dotToEnemy + " DoT-Schaden.");
                if (enemyCurrentHP <= 0) {
                    enemyCurrentHP = 0;
                    enemyAlive = false;
                    combatLog.add("Enemy wurde durch DoT besiegt!");
                    break;
                }
            }

            // Heilung anwenden
            if (playerRegen > 0 && playerCurrentHP > 0) {
                int healed = Math.min(playerRegen, playerMaxHP - playerCurrentHP);
                playerCurrentHP += healed;
                if (healed > 0) combatLog.add("Player heilt sich um " + healed + " HP.");
            }
            if (enemyRegen > 0 && enemyCurrentHP > 0) {
                int healed = Math.min(enemyRegen, enemyMaxHP - enemyCurrentHP);
                enemyCurrentHP += healed;
                if (healed > 0) combatLog.add("Enemy heilt sich um " + healed + " HP.");
            }

            // StatusEffekte updaten
            playerEff.forEach(e -> e.setRemainingTurns(e.getRemainingTurns() - 1));
            enemyEff.forEach(e -> e.setRemainingTurns(e.getRemainingTurns() - 1));
            playerEff.removeIf(e -> e.getRemainingTurns() <= 0);
            enemyEff.removeIf(e -> e.getRemainingTurns() <= 0);

            // StatusEffekte zurückschreiben
            player.getActiveEffects().clear();
            player.getActiveEffects().addAll(
                    playerEff.stream()
                            .map(StatusEffectMapper::convertStatusEffectToStatusEffectEntity)
                            .collect(Collectors.toList())
            );
            enemy.getActiveEffects().clear();
            enemy.getActiveEffects().addAll(
                    enemyEff.stream()
                            .map(StatusEffectMapper::convertStatusEffectToStatusEffectEntity)
                            .collect(Collectors.toList())
            );

            // HP speichern
            player.setCurrentHP(playerCurrentHP);
            enemy.setCurrentHP(enemyCurrentHP);

            // Entities speichern
            player = equipmentManager.saveToRepository(player);
            enemy = equipmentManager.saveToRepository(enemy);

            // Ergebnis-Resource befüllen
            RoundResultResource rr = new RoundResultResource();
            rr.setRoundId(roundId);
            rr.setStage(round.getStage());
            rr.setPlayer(actorMapper.convertActorEntityToActor(player));
            rr.setEnemy(actorMapper.convertActorEntityToActor(enemy));
            rr.setPlayerAlive(playerAlive);
            rr.setEnemyAlive(enemyAlive);
            rr.setXpGained(0);
            rr.setGoldGained(0);
            rr.setPlayerCurrentHP(playerCurrentHP);
            rr.setPlayerMaxHP(playerMaxHP);
            rr.setEnemyCurrentHP(enemyCurrentHP);
            rr.setEnemyMaxHP(enemyMaxHP);
            rr.setCombatLog(combatLog);

            // Im Repository speichern (z.B. als eigene Entity, hier als Beispiel)
            gameplayManager.saveRoundResultToRepository(gameplayMapper.convertRoundResultResourceToEntity(rr));

            roundResults.add(rr);
            roundCount++;
        }

        // Nach dem Kampf: XP/Gold vergeben, Account updaten
        int xpGained = 0;
        int goldGained = 0;
        if (playerAlive && !enemyAlive) {
            xpGained = calcXpReward(enemy.getLevel(), true);
            goldGained = calcGoldReward(enemy.getLevel(), true);
            AccountEntity acc = accountManager.findEntityInRepositoryByUsername(player.getName());
            if (acc != null) {
                acc.setXp(acc.getXp() + xpGained);
                acc.setCurrency(acc.getCurrency() + goldGained);
                accountManager.saveToRepository(acc);
            }
            roundResults.get(roundResults.size() - 1).setXpGained(xpGained);
            roundResults.get(roundResults.size() - 1).setGoldGained(goldGained);
            roundResults.get(roundResults.size() - 1).getCombatLog().add("Du hast gewonnen!");
        } else if (!playerAlive && enemyAlive) {
            roundResults.get(roundResults.size() - 1).getCombatLog().add("Du hast verloren!");
        } else {
            roundResults.get(roundResults.size() - 1).getCombatLog().add("Unentschieden!");
        }

        // Rundenanzahl im RoundEntity speichern
        round.setRoundCount(roundCount - 1);
        gameplayManager.savePowerUpToRepository(round);

        return roundResults;
    }

    private void updateActorStatsAfterRound(ActorEntity actor, int newHP) {
        // Beispiel: Alle Stats werden hier aktualisiert (Dummy-Logik, anpassen!)
        actor.setBaseHP(actor.getBaseHP());
        actor.setBaseDMG(actor.getBaseDMG());
        actor.setBaseDEF(actor.getBaseDEF());
        actor.setBaseRegen(actor.getBaseRegen());
        actor.setBaseCritRate(actor.getBaseCritRate());
        actor.setBaseCritDMG(actor.getBaseCritDMG());
        actor.setBaseLifeSteal(actor.getBaseLifeSteal());
        actor.setBaseLuck(actor.getBaseLuck());
        actor.setSpeed(actor.getSpeed());
        actor.setBurnChance(actor.getBurnChance());
        actor.setPoisonChance(actor.getPoisonChance());
        actor.setBleedChance(actor.getBleedChance());
        actor.setDotResist(actor.getDotResist());
        actor.setDotBoost(actor.getDotBoost());
        actor.setDotDurationBoost(actor.getDotDurationBoost());
        actor.setDotHeal(actor.getDotHeal());
    }

    /*public RoundResultResource fightRound(Long roundId) {
        RoundEntity round = gameplayManager.findRoundById(roundId);
        ActorEntity player = round.getPlayer();
        ActorEntity enemy = round.getEnemy();

        CombatResult result = doCombat(player, enemy);

        // HP aktualisieren
        player.setBaseHP(result.playerHp);
        enemy.setBaseHP(result.enemyHp);

        boolean playerAlive = result.playerHp > 0;
        boolean enemyAlive = result.enemyHp > 0;

        // XP und Gold berechnen
        AccountEntity account = accountManager.findEntityInRepositoryByUsername(player.getName());
        int xp = calcXpReward(enemy.getLevel(), enemyAlive == false);
        int gold = calcGoldReward(enemy.getLevel(), enemyAlive == false);

        // Belohnungen und Fortschritt vergeben
        if (!playerAlive) {
            if (currentStage > account.getHighestStage()) {
                account.setHighestStage(currentStage);
            }
            account.setXp(account.getXp() + xp);
            account.setCurrency(account.getCurrency() + gold);
            accountManager.saveToRepository(account);
            currentStage = 1;
        } else if (!enemyAlive) {
            if (currentStage > account.getHighestStage()) {
                account.setHighestStage(currentStage);
            }
            account.setXp(account.getXp() + xp);
            account.setCurrency(account.getCurrency() + gold);

            // Spieler heilt sich nach Sieg
            int heal = (int) ((player.getBaseHP() - result.playerHp) * 0.5);
            player.setBaseHP(Math.min(player.getBaseHP() + heal, player.getBaseHP()));

            accountManager.saveToRepository(account);
            currentStage++;
        }

        gameplayManager.saveToRepository(round);

        // Ergebnis-Resource befüllen
        RoundResultResource rr = new RoundResultResource();
        rr.setRoundId(round.getId());
        rr.setPlayer(actorMapper.convertActorEntityToActor(player));
        rr.setEnemy(actorMapper.convertActorEntityToActor(enemy));
        rr.setStage(round.getStage());
        rr.setPlayerAlive(playerAlive);
        rr.setEnemyAlive(enemyAlive);
        rr.setXpGained(xp);
        rr.setGoldGained(gold);

        rr.setPowerUpChoiceIds(round.getPowerUpChoices() != null
                ? round.getPowerUpChoices().stream().map(p -> p.getId().intValue()).collect(Collectors.toList())
                : null);
        // Weitere Felder wie StatusEffekte, Log etc. nach Bedarf ergänzen

        return rr;
    }*/


    // Hilfsmethoden

    private List<PowerUpEntity> getRandomPowerUps() {
        List<PowerUpEntity> all = gameplayManager.getAllPowerUps();
        Collections.shuffle(all);
        return all.subList(0, 3);
    }

    private void applyPowerUpToPlayer(ActorEntity player, PowerUpEntity powerUp) {
        switch (powerUp.getType()) {
            case HP -> player.setBaseHP(player.getBaseHP() + 20);
            case DEF -> player.setBaseDEF(player.getBaseDEF() + 2);
            case REGEN -> player.setBaseRegen(player.getBaseRegen() + 5);
            case CRITRATE -> player.setBaseCritRate(player.getBaseCritRate() + 5);
            case CRITDMG -> player.setBaseCritDMG(player.getBaseCritDMG() + 20);
            case LIFESTEAL -> player.setBaseLifeSteal(player.getBaseLifeSteal() + 2);
            case LUCK -> player.setBaseLuck(player.getBaseLuck() + 5);
            case SPEED -> player.setSpeed(player.getSpeed() + 10);
            case BURN -> player.setBurnChance(player.getBurnChance() + 3);
            case POISON -> player.setPoisonChance(player.getPoisonChance() + 3);
            case BLEED -> player.setBleedChance(player.getBleedChance() + 3);
            case DOTBOOST -> player.setDotBoost(player.getDotBoost() + 10);
            case DOTDURATIONBOOST -> player.setDotDurationBoost(player.getDotDurationBoost() + 1);
            case DOTRESIST -> player.setDotResist(player.getDotResist() + 5);
            case DOTHEAL -> player.setDotHeal(player.getDotHeal() + 5);
        }
    }

    private ActorEntity generateRandomEnemy(int stage) {
        Random rand = new Random();
        ActorEntity enemy = new ActorEntity();
        enemy.setName("Enemy_" + stage + "_" + rand.nextInt(1000));
        enemy.setLevel(stage + rand.nextInt(3));
        enemy.setBaseHP(60 + rand.nextInt(20) + stage * 10);
        enemy.setBaseDMG(8 + rand.nextInt(3) + stage * 2);
        enemy.setBaseDEF(2 + rand.nextInt(4) + stage);
        enemy.setBaseRegen(2 + rand.nextInt(4) + stage);
        enemy.setBaseCritRate(5 + rand.nextInt(6));
        enemy.setBaseCritDMG(10 + rand.nextInt(16));
        enemy.setBaseLifeSteal(rand.nextInt(5));
        enemy.setBaseLuck(rand.nextInt(5));
        enemy.setSpeed(rand.nextInt(5));
        enemy.setBurnChance(rand.nextInt(21));
        enemy.setPoisonChance(rand.nextInt(21));
        enemy.setBleedChance(rand.nextInt(21));
        enemy.setDotResist(rand.nextInt(6));
        enemy.setDotBoost(rand.nextInt(6));
        enemy.setDotDurationBoost(rand.nextInt(3));
        enemy.setDotHeal(rand.nextInt(4));
        return enemy;
    }

    private CombatResult doCombat(ActorEntity player, ActorEntity enemy) {
        Random rand = new Random();
        int playerHp = player.getBaseHP();
        int enemyHp = enemy.getBaseHP();

        // Spieler greift an
        boolean playerCrit = rand.nextDouble() < (player.getBaseCritRate() + player.getBaseLuck()) / 100.0;
        int playerDmg = player.getBaseDMG();
        if (playerCrit) playerDmg += playerDmg * player.getBaseCritDMG() / 100;
        int dmgToEnemy = Math.max(0, playerDmg - enemy.getBaseDEF());
        enemyHp -= dmgToEnemy;

        // Gegner greift an
        boolean enemyCrit = rand.nextDouble() < (enemy.getBaseCritRate() + enemy.getBaseLuck()) / 100.0;
        int enemyDmg = enemy.getBaseDMG();
        if (enemyCrit) enemyDmg += enemyDmg * enemy.getBaseCritDMG() / 100;
        int dmgToPlayer = Math.max(0, enemyDmg - player.getBaseDEF());
        playerHp -= dmgToPlayer;

        // DoT, Status, etc. können hier noch erweitert werden

        return new CombatResult(playerHp, enemyHp);
    }

    public List<PowerUpEntity> initPowerUps() {
        List<PowerUpEntity> powerUps = Arrays.asList(
                createPowerUp("HP", "Mehr HP", "Erhöht die Lebenspunkte"),
                createPowerUp("DMG", "Mehr Schaden", "Erhöht den Schaden"),
                createPowerUp("DEF", "Mehr Verteidigung", "Erhöht die Verteidigung"),
                createPowerUp("REGEN", "Regeneration", "Erhöht die Regeneration"),
                createPowerUp("CRITRATE", "Kritische Trefferchance", "Erhöht die Crit-Rate"),
                createPowerUp("CRITDMG", "Kritischer Schaden", "Erhöht den Crit-Schaden"),
                createPowerUp("LIFESTEAL", "Lebensraub", "Heilt bei Schaden"),
                createPowerUp("LUCK", "Glück", "Erhöht Glück"),
                createPowerUp("SPEED", "Geschwindigkeit", "Erhöht Speed"),
                createPowerUp("BURN", "Verbrennen", "Chance auf Verbrennen"),
                createPowerUp("POISON", "Vergiften", "Chance auf Gift"),
                createPowerUp("BLEED", "Bluten", "Chance auf Blutung"),
                createPowerUp("DOTBOOST", "DoT-Boost", "Erhöht DoT-Schaden"),
                createPowerUp("DOTDURATIONBOOST", "DoT-Dauer", "Verlängert DoT"),
                createPowerUp("DOTRESIST", "DoT-Resistenz", "Verringert DoT-Schaden"),
                createPowerUp("DOTHEAL", "DoT-Heilung", "Heilt bei DoT")
        );
        powerUps.forEach(gameplayManager::savePowerUpToRepository);
        return gameplayManager.getAllPowerUps();
    }

    private PowerUpEntity createPowerUp(String type, String name, String description) {
        PowerUpEntity p = new PowerUpEntity();
        p.setType(PowerUpTyp.valueOf(type));
        p.setName(name);
        p.setDescription(description);
        return p;
    }

    private int calcXpReward(int enemyLevel, boolean win) {
        return (win ? 50 : 20) + enemyLevel * (win ? 10 : 5);
    }

    private int calcGoldReward(int enemyLevel, boolean win) {
        return (win ? 30 : 10) + enemyLevel * (win ? 5 : 2);
    }

    private static class CombatResult {
        int playerHp, enemyHp;
        CombatResult(int p, int e) { playerHp = p; enemyHp = e; }
    }
}

