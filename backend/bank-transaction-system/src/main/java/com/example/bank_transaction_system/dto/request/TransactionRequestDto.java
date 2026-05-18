package com.example.bank_transaction_system.dto.request;
public class TransactionRequestDto {
    private Integer fromAccountId;
    private Integer toAccountId;
    private Double amount;
    public Integer getFromAccountId() {
        return fromAccountId;
    }
    public void setFromAccountId(Integer fromAccountId) {
        this.fromAccountId = fromAccountId;
    }
    public Integer getToAccountId() {
        return toAccountId;
    }
    public void setToAccountId(Integer toAccountId) {
        this.toAccountId = toAccountId;
    }
    public Double getAmount() {
        return amount;
    }
    public void setAmount(Double amount) {
        this.amount = amount;
    }
}