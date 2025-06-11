package com.bloomless.core.shopManagement.database.repositories;

import com.bloomless.core.shopManagement.database.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ItemRepository extends CrudRepository<ItemEntity, Long> {
    Optional<ItemEntity> findByName(String name);
}
