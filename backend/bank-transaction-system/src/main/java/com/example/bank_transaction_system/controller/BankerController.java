package com.example.bank_transaction_system.controller;

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

    @PostMapping("/safe-sequence")
    public List<Integer> getSafeSequence(
            @RequestBody BankerRequest request
    ) {

        return bankerAlgorithmService.getSafeSequence(
                request.getAvailable(),
                request.getAllocation(),
                request.getNeed()
        );
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