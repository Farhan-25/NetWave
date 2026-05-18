package com.example.bank_transaction_system.simulation;

import com.example.bank_transaction_system.dto.response.SimulationResponseDto;
import com.example.bank_transaction_system.service.BankerAlgorithmService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DeadlockScenarioSimulator {

    private final BankerAlgorithmService bankerAlgorithmService;

    public DeadlockScenarioSimulator(BankerAlgorithmService bankerAlgorithmService) {
        this.bankerAlgorithmService = bankerAlgorithmService;
    }

    public SimulationResponseDto runPreventionScenario() {
        SimulationResponseDto response = new SimulationResponseDto();
        response.setScenario("Concurrent Transfer Deadlock Prevention via Banker's Algorithm");

        Integer[] allocation = { 3, 2, 2 };
        Integer[] need = { 4, 2, 2 };
        Integer[] availableBefore = { 3 };

        List<String> steps = new ArrayList<>();
        steps.add("Three processes compete for shared banking resources (single-resource Banker's model)");
        steps.add("Allocation: P0=₹3, P1=₹2, P2=₹2 | Need: P0=₹4, P1=₹2, P2=₹2 | Available=₹3");

        List<Integer> sequenceBefore = bankerAlgorithmService.getSafeSequence(
                availableBefore.clone(), allocation.clone(), need.clone()
        );
        response.setSafeSequenceBefore(sequenceBefore);
        steps.add("Initial Banker's check: SAFE — execution order " + formatSequence(sequenceBefore));

        steps.add("Concurrent request: P0 attempts an additional transfer of ₹2 (reducing available to ₹1)");
        Integer[] needAfterRequest = { 4, 2, 2 };
        Integer[] availableAfter = { 1 };

        List<Integer> sequenceAfter = bankerAlgorithmService.getSafeSequence(
                availableAfter.clone(), allocation.clone(), needAfterRequest.clone()
        );

        if (sequenceAfter.isEmpty()) {
            steps.add("After granting P0's request: available=₹1, need=[4,2,2]");
            steps.add("Banker's check: UNSAFE — every process needs more than available (no safe sequence exists)");
            steps.add("Action: Request REJECTED by Banker's Algorithm — circular wait / deadlock prevented");

            response.setRejectedTransfer("P0 → ₹2");
            response.setReason("Granting this transfer drops available resources below what any process needs to complete");
            response.setDeadlockPrevented(true);
            response.setSafeSequenceAfter(new ArrayList<>());
        } else {
            steps.add("After request: still SAFE — sequence " + formatSequence(sequenceAfter));
            response.setDeadlockPrevented(false);
            response.setReason("Request would remain safe in this configuration");
            response.setSafeSequenceAfter(sequenceAfter);
        }

        response.setSteps(steps);
        return response;
    }

    private String formatSequence(List<Integer> sequence) {
        if (sequence.isEmpty()) {
            return "NONE (unsafe)";
        }
        List<String> labels = new ArrayList<>();
        for (Integer index : sequence) {
            labels.add("P" + index);
        }
        return String.join(" → ", labels);
    }
}
