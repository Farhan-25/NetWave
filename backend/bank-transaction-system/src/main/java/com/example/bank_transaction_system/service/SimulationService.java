package com.example.bank_transaction_system.service;

import com.example.bank_transaction_system.dto.response.SimulationResponseDto;
import com.example.bank_transaction_system.simulation.DeadlockScenarioSimulator;
import org.springframework.stereotype.Service;

@Service
public class SimulationService {

    private final DeadlockScenarioSimulator deadlockScenarioSimulator;

    public SimulationService(DeadlockScenarioSimulator deadlockScenarioSimulator) {
        this.deadlockScenarioSimulator = deadlockScenarioSimulator;
    }

    public SimulationResponseDto runPreventionScenario() {
        return deadlockScenarioSimulator.runPreventionScenario();
    }
}
