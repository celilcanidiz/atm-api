package com.atm.controller;

import com.atm.dto.AccountDto;
import com.atm.dto.CreateAccountRequest;
import com.atm.model.Account;
import com.atm.service.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Optional;

@RequestMapping("/accounts")
@Slf4j
@RequiredArgsConstructor
@RestController
public class AccountController {
    private final AccountService accountService;

    @PostMapping("/createAccount")
   public Account createAccount(@Valid @RequestBody CreateAccountRequest request) {
       return accountService.createAccount(request);
   }
   @DeleteMapping("/deleteAccount")
    public Account deleteAccount(@RequestParam Long accountId){
       return accountService.deleteAccount(accountId);
    }

   @PostMapping("/deposit")
    public String deposit(@RequestParam Long accountId, @RequestParam BigDecimal amount){
        accountService.deposit(accountId, amount);
        return amount + " Deposited to Account: " + accountService.getAccountsByUserId(accountId);
   }

    @PostMapping("/withdraw")
    public String withdraw(@RequestParam Long accountId, @RequestParam BigDecimal amount){
        accountService.withdraw(accountId, amount);
        return amount + " Withdrawn from Account: " + accountService.getAccountsByUserId(accountId);
    }

    @GetMapping("/getAllAccounts")
    public String getAllAccounts(){
        return accountService.gelAllAccounts().toString();
    }
    @GetMapping("/getAllAccountsByUserId")
    public String getAllAccountsByUserId(@RequestParam Long id) {
        return accountService.getAccountsByUserId(id).toString();
    }
}



