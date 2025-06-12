package com.bloomless.core.gameplayManagement.database;

import com.bloomless.core.equipmentManagement.database.ActorEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "round")
public class RoundEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int stage;
    private int roundCount;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "player_id")
    private ActorEntity player;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "enemy_id")
    private ActorEntity enemy;

    private boolean playerAlive;
    private int xpGained;
    private int goldGained;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "round_powerup",
            joinColumns = @JoinColumn(name = "round_id"),
            inverseJoinColumns = @JoinColumn(name = "powerup_id")
    )
    private List<PowerUpEntity> powerUpChoices;
}
