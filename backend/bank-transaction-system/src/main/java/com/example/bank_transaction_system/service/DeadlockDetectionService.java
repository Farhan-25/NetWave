package com.example.bank_transaction_system.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class DeadlockDetectionService {

    public List<Integer> detectCycle(Map<Integer, List<Integer>> graph) {
        Map<Integer, List<Integer>> orderedGraph = new LinkedHashMap<>(graph);
        List<Integer> path = new ArrayList<>();
        List<Integer> cycle = new ArrayList<>();

        for (Integer node : orderedGraph.keySet()) {
            path.clear();
            if (findCycle(node, orderedGraph, new ArrayList<>(), path, cycle)) {
                return extractCircularChain(path, cycle);
            }
        }
        return new ArrayList<>();
    }

    private boolean findCycle(
            Integer node,
            Map<Integer, List<Integer>> graph,
            List<Integer> visited,
            List<Integer> path,
            List<Integer> cycleStart
    ) {
        if (path.contains(node)) {
            cycleStart.add(node);
            return true;
        }
        if (visited.contains(node)) {
            return false;
        }

        visited.add(node);
        path.add(node);

        for (Integer neighbour : graph.getOrDefault(node, Collections.emptyList())) {
            if (findCycle(neighbour, graph, visited, path, cycleStart)) {
                return true;
            }
        }

        path.remove(path.size() - 1);
        return false;
    }

    private List<Integer> extractCircularChain(List<Integer> path, List<Integer> cycleStart) {
        if (cycleStart.isEmpty()) {
            return new ArrayList<>();
        }

        Integer startNode = cycleStart.get(0);
        int startIndex = path.indexOf(startNode);
        if (startIndex < 0) {
            return new ArrayList<>();
        }

        List<Integer> chain = new ArrayList<>(path.subList(startIndex, path.size()));
        chain.add(startNode);
        return chain;
    }

    public Map<Integer, List<Integer>> buildDemoDeadlockGraph() {
        Map<Integer, List<Integer>> graph = new LinkedHashMap<>();
        graph.put(0, List.of(1));
        graph.put(1, List.of(2));
        graph.put(2, List.of(0));
        return graph;
    }
}
