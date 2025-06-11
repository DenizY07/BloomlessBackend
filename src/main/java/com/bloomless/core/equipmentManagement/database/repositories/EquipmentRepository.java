package com.bloomless.core.equipmentManagement.database.repositories;

import com.bloomless.core.equipmentManagement.database.ActorEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipmentRepository extends CrudRepository<ActorEntity, Long> {
    ActorEntity findActorEntityById(Long id);
}
