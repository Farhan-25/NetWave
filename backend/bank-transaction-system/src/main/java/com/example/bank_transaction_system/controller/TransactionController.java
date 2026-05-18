package com.example.bank_transaction_system.controller;

import com.example.bank_transaction_system.dto.request.TransactionRequestDto;
import com.example.bank_transaction_system.dto.response.ScheduleResponseDto;
import com.example.bank_transaction_system.dto.response.TransactionResponseDto;
import com.example.bank_transaction_system.entity.Transaction;
import com.example.bank_transaction_system.service.ActivitySelectionService;
import com.example.bank_transaction_system.service.TransactionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
@CrossOrigin("*")
public class TransactionController {

    private final TransactionService transactionService;
    private final ActivitySelectionService activitySelectionService;

    public TransactionController(
            TransactionService transactionService,
            ActivitySelectionService activitySelectionService
    ) {
        this.transactionService = transactionService;
        this.activitySelectionService = activitySelectionService;
    }

    @PostMapping
    public TransactionResponseDto createTransaction(@RequestBody TransactionRequestDto dto) {
        return transactionService.createTransaction(dto);
    }

    @GetMapping
    public List<Transaction> getAllTransactions() {
        return transactionService.getAllTransactions();
    }

    @GetMapping("/schedule")
    public ScheduleResponseDto getOptimalSchedule() {
        List<Transaction> transactions = transactionService.getAllTransactions();
        return activitySelectionService.buildScheduleResponse(transactions);
    }
}
