package com.example.bank_transaction_system.service;

import com.example.bank_transaction_system.dto.request.TransactionRequestDto;
import com.example.bank_transaction_system.dto.response.TransactionResponseDto;
import com.example.bank_transaction_system.entity.*;
import com.example.bank_transaction_system.enums.TransactionStatus;
import com.example.bank_transaction_system.enums.TransactionType;
import com.example.bank_transaction_system.repository.AccountRepository;
import com.example.bank_transaction_system.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;
    private final BankerAlgorithmService bankerAlgorithmService;
    private final RollbackService rollbackService;

    public TransactionService(
            TransactionRepository transactionRepository,
            AccountRepository accountRepository,
            BankerAlgorithmService bankerAlgorithmService,
            RollbackService rollbackService
    ) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
        this.bankerAlgorithmService = bankerAlgorithmService;
        this.rollbackService = rollbackService;
    }

    public TransactionResponseDto createTransaction(TransactionRequestDto dto) {

        List<Account> accounts = accountRepository.findAll();

        Account sender = accountRepository.findById(dto.getFromAccountId()).orElseThrow();
        Account receiver = accountRepository.findById(dto.getToAccountId()).orElseThrow();

        Integer amount = dto.getAmount().intValue();

        Integer senderOldAllocated = sender.getAllocated();

        sender.setAllocated(senderOldAllocated + amount);
        sender.setNeed(sender.getMaxRequired() - sender.getAllocated());

        Integer[] allocation = new Integer[accounts.size()];
        Integer[] need = new Integer[accounts.size()];

        Integer totalAllocated = 0;

        for (Integer i = 0; i < accounts.size(); i++) {
            Account acc = accounts.get(i);
            allocation[i] = acc.getAllocated();
            need[i] = acc.getNeed();
            totalAllocated += acc.getAllocated();
        }

        Integer[] available = { totalAllocated };

        List<Integer> safeSequence = bankerAlgorithmService.getSafeSequence(available, allocation, need);

        TransactionResponseDto response = new TransactionResponseDto();
        response.setAmount(dto.getAmount());

        if (safeSequence.isEmpty()) {

            sender.setAllocated(senderOldAllocated);
            sender.setNeed(sender.getMaxRequired() - sender.getAllocated());

            Integer suggestedAmount = rollbackService.suggestSafeAmount(
                    amount,
                    available,
                    allocation,
                    need,
                    accounts.indexOf(sender)
            );

            response.setSafe(false);
            response.setSuggestedAmount(suggestedAmount);
            response.setMessage("Unsafe State Detected");

            return response;
        }

        sender.setSafe(true);
        receiver.setSafe(true);

        accountRepository.save(sender);
        accountRepository.save(receiver);

        Transaction transaction = new Transaction();
        transaction.setAmount(dto.getAmount());
        transaction.setFromAccount(sender);
        transaction.setToAccount(receiver);
        transaction.setStatus(TransactionStatus.SUCCESS);
        transaction.setTransactionType(TransactionType.TRANSFER);
        transaction.setStartTime(LocalDateTime.now());
        transaction.setEndTime(LocalDateTime.now().plusSeconds(5));

        Transaction saved = transactionRepository.save(transaction);

        response.setTransactionId(saved.getId());
        response.setSafe(true);
        response.setSafeSequence(safeSequence);
        response.setMessage("Safe Sequence Found");

        return response;
    }

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }
}