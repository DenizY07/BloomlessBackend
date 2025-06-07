package com.bloomless.core.accountManagement.database;

import com.bloomless.core.gameManagement.data.items.Item;
import com.bloomless.core.gameManagement.database.ItemEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "account")
public class AccountEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String username;
    private String password;
    @Column(unique = true)
    private String email;
    private int accountLevel;
    private int xp;
    private int highestStage;
    private int currency;
    private String profileImage;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "account_id") //in der item tabelle
    private List<ItemEntity> inventory = new ArrayList<>();

}
