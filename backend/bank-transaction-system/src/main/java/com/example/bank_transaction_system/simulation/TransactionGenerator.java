package com.example.bank_transaction_system.simulation;

import java.util.Random;

public class TransactionGenerator {
    private final Random random = new Random();

    public Integer generateAmount() {

        return random.nextInt(5000) + 500;
    }
}
