package com.example.bank_transaction_system.service;

import com.example.bank_transaction_system.dto.response.ScheduleResponseDto;
import com.example.bank_transaction_system.entity.Transaction;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ActivitySelectionService {

    public List<Transaction> selectTransactions(List<Transaction> transactions) {
        List<Transaction> sorted = new ArrayList<>(transactions);
        sorted.sort(Comparator.comparing(Transaction::getEndTime));

        List<Transaction> selected = new ArrayList<>();
        if (sorted.isEmpty()) {
            return selected;
        }

        selected.add(sorted.get(0));
        for (int i = 1; i < sorted.size(); i++) {
            Transaction current = sorted.get(i);
            Transaction lastSelected = selected.get(selected.size() - 1);
            if (!current.getStartTime().isBefore(lastSelected.getEndTime())) {
                selected.add(current);
            }
        }
        return selected;
    }

    public ScheduleResponseDto buildScheduleResponse(List<Transaction> transactions) {
        List<Transaction> selected = selectTransactions(transactions);
        ScheduleResponseDto response = new ScheduleResponseDto();

        response.setAllTransactions(transactions);
        response.setSelectedTransactions(selected);
        response.setTotalTransactions(transactions.size());
        response.setMaxNonConflicting(selected.size());
        response.setAlgorithm("Greedy Activity Selection (sort by finish time, pick non-overlapping)");

        if (!transactions.isEmpty()) {
            LocalDateTime windowStart = transactions.stream()
                    .map(Transaction::getStartTime)
                    .min(LocalDateTime::compareTo)
                    .orElse(null);
            LocalDateTime windowEnd = transactions.stream()
                    .map(Transaction::getEndTime)
                    .max(LocalDateTime::compareTo)
                    .orElse(null);
            response.setWindowStart(windowStart);
            response.setWindowEnd(windowEnd);
        }

        return response;
    }

    public Set<Integer> getSelectedIds(List<Transaction> selected) {
        Set<Integer> ids = new HashSet<>();
        for (Transaction tx : selected) {
            ids.add(tx.getId());
        }
        return ids;
    }
}
