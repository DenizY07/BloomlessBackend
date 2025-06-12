package com.bloomless.core.gameplayManagement.data;

import lombok.Data;

@Data
public class PowerUp {
    private Long id;
    private String name;
    private String description;
    private PowerUpTyp type;
}
