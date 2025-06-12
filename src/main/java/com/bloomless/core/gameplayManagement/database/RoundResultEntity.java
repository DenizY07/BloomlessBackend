package com.bloomless.core.gameplayManagement.database;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "round_result")
public class RoundResultEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int stage;

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
    @ElementCollection
    private List<String> playerStatusEffects;
    @ElementCollection
    private List<String> enemyStatusEffects;

    // Kampf-Flags
    private boolean playerAlive;
    private boolean enemyAlive;
    private boolean playerCriticalHit;
    private boolean enemyCriticalHit;

    // Belohnungen
    private int xpGained;
    private int goldGained;
    @ElementCollection
    private List<Integer> powerUpChoiceIds;

    // Optional: Log für das Frontend
    @ElementCollection
    private List<String> combatLog;
}