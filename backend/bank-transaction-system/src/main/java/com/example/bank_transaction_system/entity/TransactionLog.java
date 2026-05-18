package com.example.bank_transaction_system.entity;

import com.example.bank_transaction_system.enums.TransactionStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "transaction_logs")
public class TransactionLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private LocalDateTime timeStamp;

    @Enumerated(EnumType.STRING)
    private TransactionStatus status;

    public TransactionLog() {
    }

    public TransactionLog(Integer id, String name, LocalDateTime timeStamp, TransactionStatus status) {
        this.id = id;
        this.name = name;
        this.timeStamp = timeStamp;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    public TransactionStatus getStatus() {
        return status;
    }

    public void setStatus(TransactionStatus status) {
        this.status = status;
    }
}
