package com.example.bank_transaction_system.dto.response;

import java.util.List;

public class BankerResponseDto {

    private Boolean safe;
    private String message;
    private List<Integer> safeSequence;
    private Integer[] allocation;
    private Integer[] need;
    private Integer available;
    private List<String> accountNames;

    public BankerResponseDto() {
    }

    public BankerResponseDto(Boolean safe, String message, List<Integer> safeSequence) {
        this.safe = safe;
        this.message = message;
        this.safeSequence = safeSequence;
    }

    public Boolean getSafe() {
        return safe;
    }

    public void setSafe(Boolean safe) {
        this.safe = safe;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Integer> getSafeSequence() {
        return safeSequence;
    }

    public void setSafeSequence(List<Integer> safeSequence) {
        this.safeSequence = safeSequence;
    }

    public Integer[] getAllocation() {
        return allocation;
    }

    public void setAllocation(Integer[] allocation) {
        this.allocation = allocation;
    }

    public Integer[] getNeed() {
        return need;
    }

    public void setNeed(Integer[] need) {
        this.need = need;
    }

    public Integer getAvailable() {
        return available;
    }

    public void setAvailable(Integer available) {
        this.available = available;
    }

    public List<String> getAccountNames() {
        return accountNames;
    }

    public void setAccountNames(List<String> accountNames) {
        this.accountNames = accountNames;
    }
}
