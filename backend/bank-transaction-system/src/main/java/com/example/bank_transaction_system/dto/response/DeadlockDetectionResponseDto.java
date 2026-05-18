package com.example.bank_transaction_system.dto.response;

import java.util.List;
import java.util.Map;

public class DeadlockDetectionResponseDto {

    private Boolean deadlocked;
    private List<Integer> circularWaitChain;
    private List<String> circularWaitLabels;
    private String description;
    private Map<String, List<String>> waitForGraph;

    public DeadlockDetectionResponseDto() {
    }

    public Boolean getDeadlocked() {
        return deadlocked;
    }

    public void setDeadlocked(Boolean deadlocked) {
        this.deadlocked = deadlocked;
    }

    public List<Integer> getCircularWaitChain() {
        return circularWaitChain;
    }

    public void setCircularWaitChain(List<Integer> circularWaitChain) {
        this.circularWaitChain = circularWaitChain;
    }

    public List<String> getCircularWaitLabels() {
        return circularWaitLabels;
    }

    public void setCircularWaitLabels(List<String> circularWaitLabels) {
        this.circularWaitLabels = circularWaitLabels;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Map<String, List<String>> getWaitForGraph() {
        return waitForGraph;
    }

    public void setWaitForGraph(Map<String, List<String>> waitForGraph) {
        this.waitForGraph = waitForGraph;
    }
}
