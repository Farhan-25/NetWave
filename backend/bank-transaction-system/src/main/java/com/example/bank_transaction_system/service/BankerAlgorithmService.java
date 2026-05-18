package com.example.bank_transaction_system.service;

import com.example.bank_transaction_system.algorithm.BankersAlgorithm;
import com.example.bank_transaction_system.dto.response.BankerResponseDto;
import com.example.bank_transaction_system.entity.Account;
import com.example.bank_transaction_system.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BankerAlgorithmService {

    private final BankersAlgorithm bankersAlgorithm = new BankersAlgorithm();
    private final AccountRepository accountRepository;

    public BankerAlgorithmService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public List<Integer> getSafeSequence(
            Integer[] available,
            Integer[] allocation,
            Integer[] need
    ) {
        return bankersAlgorithm.getSafeSequence(available, allocation, need);
    }

    public BankerResponseDto evaluateCurrentState() {
        List<Account> accounts = accountRepository.findAll();
        BankerResponseDto response = new BankerResponseDto();

        if (accounts.isEmpty()) {
            response.setSafe(true);
            response.setMessage("No accounts — system is trivially safe");
            response.setSafeSequence(new ArrayList<>());
            response.setAllocation(new Integer[0]);
            response.setNeed(new Integer[0]);
            response.setAvailable(0);
            response.setAccountNames(new ArrayList<>());
            return response;
        }

        int n = accounts.size();
        Integer[] allocation = new Integer[n];
        Integer[] need = new Integer[n];
        List<String> accountNames = new ArrayList<>();
        int totalAllocated = 0;

        for (int i = 0; i < n; i++) {
            Account acc = accounts.get(i);
            allocation[i] = acc.getAllocated();
            need[i] = acc.getNeed();
            totalAllocated += acc.getAllocated();
            accountNames.add(acc.getAccountHolder() + " (P" + i + ")");
        }

        Integer[] available = { totalAllocated };
        List<Integer> safeSequence = getSafeSequence(available, allocation, need);

        response.setAllocation(allocation);
        response.setNeed(need);
        response.setAvailable(totalAllocated);
        response.setAccountNames(accountNames);
        response.setSafeSequence(safeSequence);
        response.setSafe(!safeSequence.isEmpty());

        if (safeSequence.isEmpty()) {
            response.setMessage("Unsafe state — no safe sequence exists for concurrent transfers");
        } else {
            List<String> labels = new ArrayList<>();
            for (Integer index : safeSequence) {
                labels.add("P" + index);
            }
            response.setMessage("Safe state — execution order: " + String.join(" → ", labels));
        }

        return response;
    }
}
