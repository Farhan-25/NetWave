package com.example.bank_transaction_system.service;
import com.example.bank_transaction_system.algorithm.BankersAlgorithm;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BankerAlgorithmService {
    private final BankersAlgorithm
            bankersAlgorithm =
            new BankersAlgorithm();
    public List<Integer> getSafeSequence(
            Integer[] available,
            Integer[] allocation,
            Integer[] need
    ) {
        return bankersAlgorithm
                .getSafeSequence(
                        available,
                        allocation,
                        need
                );
    }
}
