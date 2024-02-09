package com.atm.service;


import com.atm.dto.AccountDto;
import com.atm.dto.CreateAccountRequest;
import com.atm.model.Account;
import com.atm.model.Transaction;
import com.atm.model.TransactionType;
import com.atm.model.User;
import com.atm.repository.AccountRepository;
import com.atm.repository.TransactionRepository;
import com.atm.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.hibernate.internal.util.collections.CollectionHelper.setOf;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountService {

    private final UserRepository userRepository;
    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    public Account createAccount(CreateAccountRequest createAccountRequest) {

        User user = null;
        try {
            Optional<User> userById = userRepository.findById(createAccountRequest.user_id());
            if (userById.isPresent()) {
                user = userById.get();
            }
        } catch (Exception e) {
            log.info("User not found");
            throw new RuntimeException("User not found");
        }

        Account account = Account.builder()
                .user(user)
                .balance(createAccountRequest.balance())
                .build();

        log.info("Account Created");
        return accountRepository.save(account);
    }

    public List<AccountDto> gelAllAccounts(){
        return accountRepository.findAll()
                .stream()
                .map(account -> {
                    AccountDto accountDto = new AccountDto(0L,new BigDecimal("0.01"), LocalDate.now().atStartOfDay(), LocalDate.now().atStartOfDay());
                    accountDto = AccountDto.builder()
                            .user_id(account.getUser().getId())
                            .balance(account.getBalance())
                            .created_at(account.getCreated_at())
                            .build();
                    return accountDto;
                })
                .toList();
    }
    public List<Account> getAccountsByUserId(Long id){
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()){
            return accountRepository.findAllAccountsByUser_Id(id);
        }
        log.info("User not found");
        throw new RuntimeException("User not found");
    }

    public  String withdraw(Long accountId, BigDecimal amount){
        Optional<Account> account = accountRepository.findById(accountId);
        if(account.isPresent()){
            Account account1 = account.get();
            BigDecimal balance = account1.getBalance();
            balance = balance.subtract(amount);
            account1.setBalance(balance);
            if(balance.compareTo(BigDecimal.ZERO) < 0){
                log.info("Insufficient balance to withdraw");
                return "Insufficient balance";
            }
            accountRepository.save(account1);
            Transaction transaction = Transaction.builder()
                    .account(account1)
                    .amount(amount)
                    .type(setOf(TransactionType.WITHDRAWAL))
                    .build();
            transactionRepository.save(transaction);
            return "Account: " + accountId + " Balance: " + balance + " Withdrawn";
        }
        return "Account not found";
    }

    public String deposit(Long accountId, BigDecimal amount){
        Optional<Account> account = accountRepository.findById(accountId);
        if(account.isPresent()){
            Account account1 = account.get();
            BigDecimal balance = account1.getBalance();
            balance = balance.add(amount);
            account1.setBalance(balance);
            accountRepository.save(account1);
            Transaction transaction = Transaction.builder()
                    .account(account1)
                    .amount(amount)
                    .type(setOf(TransactionType.DEPOSIT))
                    .build();

            transactionRepository.save(transaction);
            return "Account: " + accountId + " Balance: " + balance + " Deposited";
    }
        return "Account not found";}

    public Account deleteAccount(Long accountId){
        Optional<Account> account = accountRepository.findById(accountId);
        if(account.isPresent()){
            Account account1 = account.get();
            accountRepository.delete(account1);
            log.info("Account Deleted");
            return account1;
        }
        log.info("Account not found to delete");
        throw new RuntimeException("Account not found");
    }
}
