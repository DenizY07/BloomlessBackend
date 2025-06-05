package com.bloomless.core.gameManagement.database.repositories;

import com.bloomless.core.gameManagement.database.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<ItemEntity, Long> {
}
