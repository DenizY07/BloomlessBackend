package com.bloomless.core.shopManagement.database.repositories;

import com.bloomless.core.shopManagement.database.ShopItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopItemRepository extends CrudRepository<ShopItemEntity, Long> {
    boolean existsByName(String name);
}
