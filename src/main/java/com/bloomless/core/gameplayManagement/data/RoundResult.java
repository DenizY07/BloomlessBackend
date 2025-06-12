package com.bloomless.core.gameplayManagement.data;

import lombok.Data;

import java.util.List;

@Data
public class RoundResult {
    private Long roundId;
    private int stage;

    // Spieler- und Gegner-Status
    private int playerCurrentHP;
    private int playerMaxHP;
    private int enemyCurrentHP;
    private int enemyMaxHP;

    // Schaden & Heilung
    private int damageDealt;         // Gesamtschaden an Gegner in dieser Runde
    private int damageTaken;         // Gesamtschaden am Spieler in dieser Runde
    private int dotDamageDealt;      // DoT-Schaden an Gegner in dieser Runde
    private int dotDamageTaken;      // DoT-Schaden am Spieler in dieser Runde
    private int healingDone;         // Heilung des Spielers in dieser Runde (z.B. durch LifeSteal, DoT-Heal)
    private int healingReceived;     // Heilung des Gegners in dieser Runde

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

    // Optional: Log für das Frontend
    private List<String> combatLog;


}
