package com.example.bank_transaction_system.dto.response;

import com.example.bank_transaction_system.entity.Transaction;

import java.time.LocalDateTime;
import java.util.List;

public class ScheduleResponseDto {

    private List<Transaction> allTransactions;
    private List<Transaction> selectedTransactions;
    private Integer totalTransactions;
    private Integer maxNonConflicting;
    private LocalDateTime windowStart;
    private LocalDateTime windowEnd;
    private String algorithm;

    public ScheduleResponseDto() {
    }

    public List<Transaction> getAllTransactions() {
        return allTransactions;
    }

    public void setAllTransactions(List<Transaction> allTransactions) {
        this.allTransactions = allTransactions;
    }

    public List<Transaction> getSelectedTransactions() {
        return selectedTransactions;
    }

    public void setSelectedTransactions(List<Transaction> selectedTransactions) {
        this.selectedTransactions = selectedTransactions;
    }

    public Integer getTotalTransactions() {
        return totalTransactions;
    }

    public void setTotalTransactions(Integer totalTransactions) {
        this.totalTransactions = totalTransactions;
    }

    public Integer getMaxNonConflicting() {
        return maxNonConflicting;
    }

    public void setMaxNonConflicting(Integer maxNonConflicting) {
        this.maxNonConflicting = maxNonConflicting;
    }

    public LocalDateTime getWindowStart() {
        return windowStart;
    }

    public void setWindowStart(LocalDateTime windowStart) {
        this.windowStart = windowStart;
    }

    public LocalDateTime getWindowEnd() {
        return windowEnd;
    }

    public void setWindowEnd(LocalDateTime windowEnd) {
        this.windowEnd = windowEnd;
    }

    public String getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }
}
