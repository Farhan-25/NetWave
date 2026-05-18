package com.example.bank_transaction_system.service;
import com.example.bank_transaction_system.entity.Transaction;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class ActivitySelectionService {
    public List<Transaction> selectTransactions(
            List<Transaction> transactions
    ) {
        List<Transaction> selected =
                new ArrayList<>();
        transactions.sort(
                Comparator.comparing(
                        Transaction::getEndTime
                )
        );
        if (transactions.isEmpty()) {
            return selected;
        }
        Transaction first =
                transactions.get(0);
        selected.add(first);

        for (Integer i = 1;
             i < transactions.size();
             i++) {
            Transaction current =
                    transactions.get(i);
            Transaction lastSelected =
                    selected.get(
                            selected.size() - 1
                    );
            if (
                    current.getStartTime()
                            .isAfter(
                                    lastSelected
                                            .getEndTime()
                            )
                            ||
                            current.getStartTime()
                                    .isEqual(
                                            lastSelected
                                                    .getEndTime()
                                    )
            ) {
                selected.add(current);
            }
        }
        return selected;
    }
}