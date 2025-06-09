package com.bloomless.core.gameManagement.database;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "upgradeItem")
public class UpgradeItemEntity extends ItemEntity {
    private int givenXP;
}
