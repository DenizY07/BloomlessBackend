package com.bloomless.core.gameplayManagement.database;

import com.bloomless.core.gameplayManagement.data.PowerUpTyp;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "power_up")
public class PowerUpEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;

    @Enumerated(EnumType.STRING)
    private PowerUpTyp type;
}