package com.example.bank_transaction_system.entity;

import jakarta.persistence.*;
import jakarta.transaction.TransactionScoped;

@Entity
@Table(name = "resource_states")
public class ResourceState {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer processId;

    private Integer allocation;

    private Integer maxNeed;

    private Integer available;

    public ResourceState() {
    }

    public ResourceState(Integer id, Integer processId, Integer allocation, Integer maxNeed, Integer available) {
        this.id = id;
        this.processId = processId;
        this.allocation = allocation;
        this.maxNeed = maxNeed;
        this.available = available;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProcessId() {
        return processId;
    }

    public void setProcessId(Integer processId) {
        this.processId = processId;
    }

    public Integer getAllocation() {
        return allocation;
    }

    public void setAllocation(Integer allocation) {
        this.allocation = allocation;
    }

    public Integer getMaxNeed() {
        return maxNeed;
    }

    public void setMaxNeed(Integer maxNeed) {
        this.maxNeed = maxNeed;
    }

    public Integer getAvailable() {
        return available;
    }

    public void setAvailable(Integer available) {
        this.available = available;
    }
}
