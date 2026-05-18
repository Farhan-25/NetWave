package com.example.bank_transaction_system.dto.response;

import java.util.List;

public class BankerResponseDto {
    private Boolean safe;

    private String message;

    private List<Integer> safeSequence;

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
}
