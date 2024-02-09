package com.atm.repository;

import com.atm.model.Account;
import com.atm.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String userName);

    boolean existsByEmail(String email);

    boolean existsByUsername(String username);

    @Query("SELECT account_id FROM Account account_id WHERE account_id.user.id = ?1")
    List<Account> findAllAccountsByUser_Id(Long id);
}
