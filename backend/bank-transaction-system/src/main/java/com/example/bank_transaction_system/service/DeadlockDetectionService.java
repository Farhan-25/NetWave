package com.example.bank_transaction_system.service;
import org.springframework.stereotype.Service;
import java.util.*;
@Service
public class DeadlockDetectionService {
    public List<Integer> detectCycle(
            Map<Integer, List<Integer>> graph
    ) {
        Set<Integer> visited =
                new HashSet<>();
        Set<Integer> recursionStack =
                new HashSet<>();
        List<Integer> cycle =
                new ArrayList<>();
        for (Integer node : graph.keySet()) {
            if (
                    dfs(
                            node,
                            graph,
                            visited,
                            recursionStack,
                            cycle
                    )
            ) {
                cycle.add(node);
                return cycle;
            }
        }
        return new ArrayList<>();
    }
    private Boolean dfs(
            Integer node,
            Map<Integer, List<Integer>> graph,
            Set<Integer> visited,
            Set<Integer> recursionStack,
            List<Integer> cycle
    ) {
        if (recursionStack.contains(node)) {
            cycle.add(node);
            return true;
        }
        if (visited.contains(node)) {

            return false;
        }
        visited.add(node);
        recursionStack.add(node);
        for (Integer neighbour :
                graph.getOrDefault(
                        node,
                        new ArrayList<>()
                )) {
            if (
                    dfs(
                            neighbour,
                            graph,
                            visited,
                            recursionStack,
                            cycle
                    )
            ) {
                cycle.add(node);
                return true;
            }
        }
        recursionStack.remove(node);
        return false;
    }
}