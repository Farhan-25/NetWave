package com.example.bank_transaction_system.controller;

import com.example.bank_transaction_system.dto.response.DeadlockDetectionResponseDto;
import com.example.bank_transaction_system.service.DeadlockDetectionService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/deadlock")
public class DeadlockController {

    private final DeadlockDetectionService deadlockDetectionService;

    public DeadlockController(DeadlockDetectionService deadlockDetectionService) {
        this.deadlockDetectionService = deadlockDetectionService;
    }

    @GetMapping("/detect")
    public DeadlockDetectionResponseDto detectDemoDeadlock() {
        Map<Integer, List<Integer>> graph = deadlockDetectionService.buildDemoDeadlockGraph();
        return buildResponse(graph, "Demo: P0 waits for P1, P1 waits for P2, P2 waits for P0");
    }

    @PostMapping("/detect")
    public DeadlockDetectionResponseDto detectDeadlock(@RequestBody DeadlockGraphRequest request) {
        Map<Integer, List<Integer>> graph = new LinkedHashMap<>();
        if (request.getWaitForGraph() != null) {
            request.getWaitForGraph().forEach((key, value) ->
                    graph.put(Integer.parseInt(key), value)
            );
        }
        return buildResponse(graph, "Custom wait-for graph analysis");
    }

    private DeadlockDetectionResponseDto buildResponse(
            Map<Integer, List<Integer>> graph,
            String description
    ) {
        List<Integer> cycle = deadlockDetectionService.detectCycle(graph);
        DeadlockDetectionResponseDto response = new DeadlockDetectionResponseDto();
        response.setDeadlocked(!cycle.isEmpty());
        response.setCircularWaitChain(cycle);
        response.setDescription(description);

        Map<String, List<String>> labeledGraph = new LinkedHashMap<>();
        graph.forEach((node, edges) -> {
            List<String> labels = new ArrayList<>();
            for (Integer edge : edges) {
                labels.add("P" + edge);
            }
            labeledGraph.put("P" + node, labels);
        });
        response.setWaitForGraph(labeledGraph);

        if (!cycle.isEmpty()) {
            List<String> labels = new ArrayList<>();
            for (Integer node : cycle) {
                labels.add("P" + node);
            }
            response.setCircularWaitLabels(labels);
            response.setDescription(
                    description + " — Circular wait detected: " + String.join(" → ", labels)
            );
        } else {
            response.setCircularWaitLabels(new ArrayList<>());
            response.setDescription(description + " — No circular wait detected");
        }

        return response;
    }

    public static class DeadlockGraphRequest {
        private Map<String, List<Integer>> waitForGraph;

        public Map<String, List<Integer>> getWaitForGraph() {
            return waitForGraph;
        }

        public void setWaitForGraph(Map<String, List<Integer>> waitForGraph) {
            this.waitForGraph = waitForGraph;
        }
    }
}
