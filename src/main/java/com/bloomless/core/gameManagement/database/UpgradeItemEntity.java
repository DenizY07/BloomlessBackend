package com.bloomless.core.gameManagement.database;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Data;

@Data
@Entity
@Table(name = "upgradeItem")
public class UpgradeItemEntity extends ItemEntity {
    private int givenXP;

}
