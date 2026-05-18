package com.example.bank_transaction_system.service;

import com.example.bank_transaction_system.simulation.ConcurrentTransferSimulator;
import org.springframework.stereotype.Service;

@Service
public class SimulationService {
    public void runSimulation() {

        ConcurrentTransferSimulator simulator =
                new ConcurrentTransferSimulator();

        simulator.simulateTransfers();
    }
}
