package com.example.bank_transaction_system.simulation;

public class ConcurrentTransferSimulator {
    public void simulateTransfers() {

        Runnable transfer1 = () -> {

            System.out.println(
                    "Transfer A -> B Started"
            );
        };

        Runnable transfer2 = () -> {

            System.out.println(
                    "Transfer B -> C Started"
            );
        };

        Thread thread1 = new Thread(transfer1);

        Thread thread2 = new Thread(transfer2);

        thread1.start();

        thread2.start();
    }
}
