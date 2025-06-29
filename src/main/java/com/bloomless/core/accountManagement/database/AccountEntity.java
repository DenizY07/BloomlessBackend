package com.bloomless.core.accountManagement.database;

import com.bloomless.core.shopManagement.database.ItemEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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
    @JoinColumn(name = "account_id")
    @NotNull
    private List<ItemEntity> inventory = new ArrayList<>();

}
