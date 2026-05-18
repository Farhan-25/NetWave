package com.example.bank_transaction_system.algorithm;

import java.util.List;

public class SafetyAlgorithm {

    private final BankersAlgorithm bankersAlgorithm =
            new BankersAlgorithm();

    public Boolean isSafeState(
            Integer[] available,
            Integer[] allocation,
            Integer[] need
    ) {

        List<Integer> safeSequence =
                bankersAlgorithm.getSafeSequence(
                        available,
                        allocation,
                        need
                );

        return !safeSequence.isEmpty();
    }
}