package com.bloomless.core.equipmentManagement.database;

import com.bloomless.core.equipmentManagement.data.EffectType;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "status_effect")
public class StatusEffectEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private EffectType type;
    private int damagePerTurn;
    private int remainingTurns;

    @ManyToOne
    @JoinColumn(name = "creature_id")
    private ActorEntity creature;
}
