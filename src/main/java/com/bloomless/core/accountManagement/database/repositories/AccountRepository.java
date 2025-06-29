package com.bloomless.core.accountManagement.database.repositories;

import com.bloomless.core.accountManagement.database.AccountEntity;
import org.springframework.data.domain.Limit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends CrudRepository<AccountEntity,Long> {

    Optional<AccountEntity> findByUsername(String username);
    Optional<AccountEntity> findByEmail(String email);
}
