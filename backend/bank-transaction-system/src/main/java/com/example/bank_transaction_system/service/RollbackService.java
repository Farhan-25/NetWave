package com.example.bank_transaction_system.service;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class RollbackService {
    private final BankerAlgorithmService
            bankerAlgorithmService;
    public RollbackService(
            BankerAlgorithmService bankerAlgorithmService
    ) {
        this.bankerAlgorithmService =
                bankerAlgorithmService;
    }
    public Integer suggestSafeAmount(
            Integer originalRequest,

            Integer[] available,
            Integer[] allocation,
            Integer[] need,
            Integer senderIndex
    ) {
        for (Integer amount = originalRequest - 1;
             amount > 0;
             amount--) {
            Integer[] tempNeed =
                    need.clone();
            tempNeed[senderIndex] =
                    tempNeed[senderIndex]
                            + originalRequest
                            - amount;
            List<Integer> sequence =
                    bankerAlgorithmService
                            .getSafeSequence(
                                    available,
                                    allocation,
                                    tempNeed
                            );
            if (!sequence.isEmpty()) {
                return amount;
            }
        }
        return 0;
    }
}