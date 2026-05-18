package com.example.bank_transaction_system.entity;
import com.example.bank_transaction_system.enums.TransactionStatus;
import com.example.bank_transaction_system.enums.TransactionType;
import jakarta.persistence.*;
import java.time.LocalDateTime;
@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    private Account fromAccount;
    @ManyToOne
    private Account toAccount;
    private Double amount;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    @Enumerated(EnumType.STRING)
    private TransactionStatus status;
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Account getFromAccount() {
        return fromAccount;
    }
    public void setFromAccount(Account fromAccount) {
        this.fromAccount = fromAccount;
    }
    public Account getToAccount() {
        return toAccount;
    }
    public void setToAccount(Account toAccount) {
        this.toAccount = toAccount;
    }
    public Double getAmount() {
        return amount;
    }
    public void setAmount(Double amount) {
        this.amount = amount;
    }
    public LocalDateTime getStartTime() {
        return startTime;
    }
    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }
    public LocalDateTime getEndTime() {
        return endTime;
    }
    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }
    public TransactionStatus getStatus() {
        return status;
    }
    public void setStatus(TransactionStatus status) {
        this.status = status;
    }
    public TransactionType getTransactionType() {
        return transactionType;
    }
    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }
}
