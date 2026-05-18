package com.example.bank_transaction_system.repository;

import com.example.bank_transaction_system.entity.Transaction;
import com.example.bank_transaction_system.enums.TransactionStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction,Integer> {
    List<Transaction> findByStatus(TransactionStatus status);
}
