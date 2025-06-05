package com.bloomless.core.accountManagement.database.repositories;

import com.bloomless.core.accountManagement.database.AccountEntity;
import org.springframework.data.domain.Limit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity,Long> {

    Optional<AccountEntity> findByUsername(String username);

    Optional<AccountEntity> findByEmail(String email, Limit limit);

    Optional<AccountEntity> findByEmail(String email);

    Optional<AccountEntity> findByPassword(String password);
}
