package com.example.bank_transaction_system.mapper;
import com.example.bank_transaction_system.dto.request.AccountRequestDto;
import com.example.bank_transaction_system.dto.response.AccountResponseDto;
import com.example.bank_transaction_system.entity.Account;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    AccountResponseDto toAccountDto(Account account);
    Account toAccount(AccountRequestDto dto);
}
