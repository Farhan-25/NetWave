package com.example.bank_transaction_system.simulation;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadTransactionExecutor {
    public void executeTransactions() {

        ExecutorService executorService =
                Executors.newFixedThreadPool(5);

        for (Integer i = 0; i < 20; i++) {

            Integer transactionId = i;

            executorService.execute(() -> {

                System.out.println(
                        "Executing Transaction : "
                                + transactionId
                );
            });
        }

        executorService.shutdown();
    }
}
