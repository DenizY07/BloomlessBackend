package com.bloomless.core.gameplayManagement.rest.dtos;

import com.bloomless.core.gameplayManagement.data.PowerUpTyp;
import lombok.Data;

@Data
public class PowerUpDto {
    private Long id;
    private String name;
    private String description;
    private PowerUpTyp type;
}
