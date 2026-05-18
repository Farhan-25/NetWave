package com.example.bank_transaction_system.entity;
import jakarta.persistence.*;

@Entity
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String accountHolder;
    private Integer allocated;
    private Integer maxRequired;
    private Integer need;
    private Boolean safe;
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
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
    public Integer getNeed() {
        return need;
    }
    public void setNeed(Integer need) {
        this.need = need;
    }
    public Boolean getSafe() {
        return safe;
    }
    public void setSafe(Boolean safe) {
        this.safe = safe;
    }
}