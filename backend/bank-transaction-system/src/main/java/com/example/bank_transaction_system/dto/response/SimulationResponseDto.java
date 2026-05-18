package com.example.bank_transaction_system.dto.response;

import java.util.List;

public class SimulationResponseDto {

    private String scenario;
    private List<String> steps;
    private Boolean deadlockPrevented;
    private String rejectedTransfer;
    private String reason;
    private List<Integer> safeSequenceBefore;
    private List<Integer> safeSequenceAfter;

    public SimulationResponseDto() {
    }

    public String getScenario() {
        return scenario;
    }

    public void setScenario(String scenario) {
        this.scenario = scenario;
    }

    public List<String> getSteps() {
        return steps;
    }

    public void setSteps(List<String> steps) {
        this.steps = steps;
    }

    public Boolean getDeadlockPrevented() {
        return deadlockPrevented;
    }

    public void setDeadlockPrevented(Boolean deadlockPrevented) {
        this.deadlockPrevented = deadlockPrevented;
    }

    public String getRejectedTransfer() {
        return rejectedTransfer;
    }

    public void setRejectedTransfer(String rejectedTransfer) {
        this.rejectedTransfer = rejectedTransfer;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public List<Integer> getSafeSequenceBefore() {
        return safeSequenceBefore;
    }

    public void setSafeSequenceBefore(List<Integer> safeSequenceBefore) {
        this.safeSequenceBefore = safeSequenceBefore;
    }

    public List<Integer> getSafeSequenceAfter() {
        return safeSequenceAfter;
    }

    public void setSafeSequenceAfter(List<Integer> safeSequenceAfter) {
        this.safeSequenceAfter = safeSequenceAfter;
    }
}
