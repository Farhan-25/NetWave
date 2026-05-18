package com.example.bank_transaction_system.dto.request;
public class AccountRequestDto {
    private String accountHolder;
    private Integer allocated;
    private Integer maxRequired;
    public String getAccountHolder() {
        return accountHolder;
    }
    public void setAccountHolder(String accountHolder) {
        this.accountHolder = accountHolder;
    }
    public Integer getAllocated() {
        return allocated;
    }
    public void setAllocated(Integer allocated) {
        this.allocated = allocated;
    }
    public Integer getMaxRequired() {
        return maxRequired;
    }
    public void setMaxRequired(Integer maxRequired) {
        this.maxRequired = maxRequired;
    }
}
