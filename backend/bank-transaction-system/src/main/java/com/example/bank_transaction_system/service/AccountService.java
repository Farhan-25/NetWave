package com.example.bank_transaction_system.service;
import com.example.bank_transaction_system.dto.request.AccountRequestDto;
import com.example.bank_transaction_system.dto.response.AccountResponseDto;
import com.example.bank_transaction_system.entity.Account;
import com.example.bank_transaction_system.mapper.AccountMapper;
import com.example.bank_transaction_system.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AccountService {
    private final AccountMapper accountMapper;
    private final AccountRepository accountRepository;
    public AccountService(
            AccountMapper accountMapper,
            AccountRepository accountRepository
    ) {
        this.accountMapper = accountMapper;
        this.accountRepository = accountRepository;
    }
    public AccountResponseDto createAccount(
            AccountRequestDto dto
    ) {
        Account account =
                accountMapper.toAccount(dto);
        account.setNeed(
                dto.getMaxRequired()
                        - dto.getAllocated()
        );
        account.setSafe(true);
        Account saved =
                accountRepository.save(account);
        return accountMapper
                .toAccountDto(saved);
    }
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }
    public void deleteAccount(Integer id) {
        accountRepository.deleteById(id);
    }
}
