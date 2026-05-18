package com.example.bank_transaction_system.repository;

import com.example.bank_transaction_system.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account,Integer> {
    Optional<Account> findByAccountHolder(String accountHolder);
}
