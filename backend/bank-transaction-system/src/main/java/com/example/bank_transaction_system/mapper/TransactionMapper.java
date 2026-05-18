package com.example.bank_transaction_system.mapper;

import com.example.bank_transaction_system.dto.request.TransactionRequestDto;
import com.example.bank_transaction_system.dto.response.TransactionResponseDto;
import com.example.bank_transaction_system.entity.Transaction;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TransactionMapper {

    public Transaction toTransaction(TransactionRequestDto transactionRequestDto);
    public TransactionResponseDto toTransactionDto(Transaction transaction);
}
