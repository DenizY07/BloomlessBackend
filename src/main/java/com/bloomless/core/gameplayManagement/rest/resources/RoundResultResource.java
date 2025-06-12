package com.bloomless.core.gameplayManagement.rest.resources;


import com.bloomless.core.equipmentManagement.data.Actor;
import lombok.Data;

import java.util.List;

@Data
public class RoundResultResource {
    private Long roundId;
    private int stage;

    private Actor player;
    private Actor Enemy;

    // Spieler- und Gegner-Status
    private int playerCurrentHP;
    private int playerMaxHP;
    private int enemyCurrentHP;
    private int enemyMaxHP;

    // Schaden & Heilung
    private int damageDealt;
    private int damageTaken;
    private int dotDamageDealt;
    private int dotDamageTaken;
    private int healingDone;
    private int healingReceived;

    // Status-Effekte
    private List<String> playerStatusEffects;
    private List<String> enemyStatusEffects;

    // Kampf-Flags
    private boolean playerAlive;
    private boolean enemyAlive;
    private boolean playerCriticalHit;
    private boolean enemyCriticalHit;

    // Belohnungen
    private int xpGained;
    private int goldGained;
    private List<Integer> powerUpChoiceIds;

    // Kampflog
    private List<String> combatLog;
}