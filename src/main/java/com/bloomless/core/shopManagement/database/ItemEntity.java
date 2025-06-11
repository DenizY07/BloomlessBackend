package com.bloomless.core.shopManagement.database;

import com.bloomless.core.accountManagement.database.AccountEntity;
import com.bloomless.core.shopManagement.data.items.Rarity;
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
    private String type;
    private String name;
    @Enumerated(EnumType.STRING)
    private Rarity rarity;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "account_id")
    private AccountEntity account;
}
