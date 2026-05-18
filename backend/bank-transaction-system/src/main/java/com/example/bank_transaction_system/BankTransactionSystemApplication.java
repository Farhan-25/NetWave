package com.example.bank_transaction_system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class BankTransactionSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankTransactionSystemApplication.class, args);
	}

}
