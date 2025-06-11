package com.bloomless.core.equipmentManagement.manager;

import com.bloomless.core.equipmentManagement.database.ActorEntity;
import com.bloomless.core.equipmentManagement.database.repositories.EquipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EquipmentManager {
    @Autowired
    EquipmentRepository equipmentRepository;

    public ActorEntity findActorInRepositoryById(Long actorId){
        return equipmentRepository.findActorEntityById(actorId);
    }

    public ActorEntity saveToRepository(ActorEntity entity){
        return equipmentRepository.save(entity);
    }

    public List<ActorEntity> getAllPlayer(){
        List<ActorEntity> list = new ArrayList<>();
        equipmentRepository.findAll().forEach(list::add);
        return list;
    }

}
