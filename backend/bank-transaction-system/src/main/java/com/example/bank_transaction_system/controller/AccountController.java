package com.example.bank_transaction_system.controller;
import com.example.bank_transaction_system.dto.request.AccountRequestDto;
import com.example.bank_transaction_system.dto.response.AccountResponseDto;
import com.example.bank_transaction_system.entity.Account;
import com.example.bank_transaction_system.service.AccountService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/accounts")
public class AccountController {
    private final AccountService
            accountService;
    public AccountController(
            AccountService accountService
    ) {
        this.accountService =
                accountService;
    }
    @PostMapping
    public AccountResponseDto createAccount(
            @RequestBody AccountRequestDto dto
    ) {
        return accountService
                .createAccount(dto);
    }
    @GetMapping
    public List<Account> getAllAccounts() {
        return accountService
                .getAllAccounts();
    }
    @DeleteMapping("/{id}")
    public String deleteAccount(
            @PathVariable Integer id
    ) {
        accountService
                .deleteAccount(id);
        return "Account Deleted";
    }
}
