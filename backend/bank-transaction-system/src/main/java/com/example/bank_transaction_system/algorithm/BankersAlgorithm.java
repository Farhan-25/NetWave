package com.example.bank_transaction_system.algorithm;
import java.util.ArrayList;
import java.util.List;

public class BankersAlgorithm {
    public List<Integer> getSafeSequence(
            Integer[] available,
            Integer[] allocation,
            Integer[] need
    ) {
        Integer n = allocation.length;
        Integer work = available[0];
        Boolean[] finished = new Boolean[n];
        List<Integer> safeSequence =
                new ArrayList<>();
        for (Integer i = 0; i < n; i++) {
            finished[i] = false;
        }
        Integer completed = 0;
        while (completed < n) {
            Boolean found = false;
            for (Integer i = 0;
                 i < n;
                 i++) {
                if (!finished[i]
                        && need[i] <= work) {
                    work =
                            work + allocation[i];
                    safeSequence.add(i);
                    finished[i] = true;
                    completed++;
                    found = true;
                }
            }
            if (!found) {
                return new ArrayList<>();
            }
        }
        return safeSequence;
    }
}
