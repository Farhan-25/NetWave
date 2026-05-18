package com.example.bank_transaction_system.controller;

import com.example.bank_transaction_system.dto.response.BankerResponseDto;
import com.example.bank_transaction_system.service.BankerAlgorithmService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/banker")
public class BankerController {

    private final BankerAlgorithmService bankerAlgorithmService;

    public BankerController(BankerAlgorithmService bankerAlgorithmService) {
        this.bankerAlgorithmService = bankerAlgorithmService;
    }

    @GetMapping("/state")
    public BankerResponseDto getCurrentState() {
        return bankerAlgorithmService.evaluateCurrentState();
    }

    @PostMapping("/safe-sequence")
    public BankerResponseDto getSafeSequence(@RequestBody BankerRequest request) {
        List<Integer> sequence = bankerAlgorithmService.getSafeSequence(
                request.getAvailable(),
                request.getAllocation(),
                request.getNeed()
        );

        BankerResponseDto response = new BankerResponseDto();
        response.setAllocation(request.getAllocation());
        response.setNeed(request.getNeed());
        response.setAvailable(request.getAvailable()[0]);
        response.setSafeSequence(sequence);
        response.setSafe(!sequence.isEmpty());
        response.setMessage(sequence.isEmpty()
                ? "Unsafe state — no safe sequence exists"
                : "Safe state — valid execution order found");
        return response;
    }

    public static class BankerRequest {

        private Integer[] available;
        private Integer[] allocation;
        private Integer[] need;

        public Integer[] getAvailable() {
            return available;
        }

        public void setAvailable(Integer[] available) {
            this.available = available;
        }

        public Integer[] getAllocation() {
            return allocation;
        }

        public void setAllocation(Integer[] allocation) {
            this.allocation = allocation;
        }

        public Integer[] getNeed() {
            return need;
        }

        public void setNeed(Integer[] need) {
            this.need = need;
        }
    }
}
