package com.bloomless.core.gameplayManagement.rest.resources;


import com.bloomless.core.gameplayManagement.data.PowerUpTyp;
import lombok.Data;

@Data
public class PowerUpResource {
    private Long id;
    private String name;
    private String description;
    private PowerUpTyp type;
}
