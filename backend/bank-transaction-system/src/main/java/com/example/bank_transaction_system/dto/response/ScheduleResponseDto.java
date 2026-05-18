package com.example.bank_transaction_system.dto.response;

import java.util.List;

public class ScheduleResponseDto {
    private List<Integer> scheduledTransactionIds;

    private Integer totalSelected;

    public ScheduleResponseDto() {
    }

    public ScheduleResponseDto(List<Integer> scheduledTransactionIds, Integer totalSelected) {
        this.scheduledTransactionIds = scheduledTransactionIds;
        this.totalSelected = totalSelected;
    }

    public List<Integer> getScheduledTransactionIds() {
        return scheduledTransactionIds;
    }

    public void setScheduledTransactionIds(List<Integer> scheduledTransactionIds) {
        this.scheduledTransactionIds = scheduledTransactionIds;
    }

    public Integer getTotalSelected() {
        return totalSelected;
    }

    public void setTotalSelected(Integer totalSelected) {
        this.totalSelected = totalSelected;
    }
}
