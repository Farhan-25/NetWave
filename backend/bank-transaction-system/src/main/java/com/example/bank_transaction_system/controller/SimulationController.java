package com.example.bank_transaction_system.controller;

import com.example.bank_transaction_system.dto.response.SimulationResponseDto;
import com.example.bank_transaction_system.service.SimulationService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/simulation")
public class SimulationController {

    private final SimulationService simulationService;

    public SimulationController(SimulationService simulationService) {
        this.simulationService = simulationService;
    }

    @PostMapping("/run")
    public SimulationResponseDto runSimulation() {
        return simulationService.runPreventionScenario();
    }
}
