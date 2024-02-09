package com.atm.service;

import com.atm.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransactionService {

    private final TransactionRepository transactionRepository;
    public String deleteTransaction(Long id) {
        transactionRepository.deleteById(id);
        return "Id: " + id + "Transaction deleted";
    }
}
