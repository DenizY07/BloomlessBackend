package com.bloomless.core.shopManagement.database;

import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class ShopItemEntity extends ItemEntity {

}
