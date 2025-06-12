package com.bloomless.core.gameplayManagement.database.repositories;

import com.bloomless.core.gameplayManagement.data.RoundResult;
import com.bloomless.core.gameplayManagement.database.RoundResultEntity;
import org.springframework.data.repository.CrudRepository;

public interface RoundResultRepository extends CrudRepository<RoundResultEntity,Long> {
}
