package com.bloomless.core.gameManagement.database;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "dmgItem")
public class DMGItemEntity extends CombatItemEntity {
    private int itemDMG;
    private double itemCritRate;
    private int itemCritDMG;
}
