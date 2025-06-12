package com.bloomless.core.gameplayManagement.database;


import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "game_environment")
public class GameEnvironmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean playerAlive;
    private int xpGained;
    private int goldGained;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "game_environment_powerup",
            joinColumns = @JoinColumn(name = "game_environment_id"),
            inverseJoinColumns = @JoinColumn(name = "powerup_id")
    )
    private List<PowerUpEntity> powerUpChoices;
}
