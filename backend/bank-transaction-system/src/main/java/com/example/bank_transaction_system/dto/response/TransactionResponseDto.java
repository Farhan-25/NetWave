package com.example.bank_transaction_system.dto.response;
import java.util.List;

public class TransactionResponseDto {
    private Integer transactionId;
    private Double amount;
    private Boolean safe;
    private List<Integer> safeSequence;
    private Integer suggestedAmount;
    private String message;
    public Integer getTransactionId() {
        return transactionId;
    }
    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }
    public Double getAmount() {
        return amount;
    }
    public void setAmount(Double amount) {
        this.amount = amount;
    }
    public Boolean getSafe() {
        return safe;
    }
    public void setSafe(Boolean safe) {
        this.safe = safe;
    }
    public List<Integer> getSafeSequence() {
        return safeSequence;
    }
    public void setSafeSequence(List<Integer> safeSequence) {
        this.safeSequence = safeSequence;
    }
    public Integer getSuggestedAmount() {

        return suggestedAmount;
    }
    public void setSuggestedAmount(Integer suggestedAmount) {
        this.suggestedAmount = suggestedAmount;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
}
