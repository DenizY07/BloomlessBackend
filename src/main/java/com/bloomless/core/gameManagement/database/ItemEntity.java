package com.bloomless.core.gameManagement.database;

import com.bloomless.core.gameManagement.data.items.Rarity;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "item")
@Inheritance(strategy = InheritanceType.JOINED)
public class ItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private Rarity rarity;
}
