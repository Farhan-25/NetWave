package com.example.bank_transaction_system.algorithm;

import com.example.bank_transaction_system.entity.Transaction;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class GreedyActivitySelector {
    public List<Transaction> selectMaximumTransactions(
            List<Transaction> transactions
    ) {

        transactions.sort(
                Comparator.comparing(Transaction::getEndTime)
        );

        List<Transaction> selectedTransactions =
                new ArrayList<>();

        if (transactions.isEmpty()) {
            return selectedTransactions;
        }

        Transaction firstTransaction =
                transactions.get(0);

        selectedTransactions.add(firstTransaction);

        for (Integer i = 1; i < transactions.size(); i++) {

            Transaction currentTransaction =
                    transactions.get(i);

            Transaction lastSelectedTransaction =
                    selectedTransactions.get(
                            selectedTransactions.size() - 1
                    );

            if (!currentTransaction.getStartTime()
                    .isBefore(
                            lastSelectedTransaction.getEndTime()
                    )) {

                selectedTransactions.add(
                        currentTransaction
                );
            }
        }

        return selectedTransactions;
    }
}
