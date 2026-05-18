package com.example.bank_transaction_system.controller;

import com.example.bank_transaction_system.entity.Transaction;
import com.example.bank_transaction_system.service.ActivitySelectionService;
import com.example.bank_transaction_system.service.TransactionService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/schedule")
public class SchedulerController {

    private final ActivitySelectionService
            activitySelectionService;

    private final TransactionService
            transactionService;

    public SchedulerController(
            ActivitySelectionService activitySelectionService,
            TransactionService transactionService
    ) {

        this.activitySelectionService =
                activitySelectionService;

        this.transactionService =
                transactionService;
    }

    @GetMapping
    public List<Transaction>
    scheduleTransactions() {

        List<Transaction> transactions =
                transactionService
                        .getAllTransactions();

        return activitySelectionService
                .selectTransactions(
                        transactions
                );
    }
}