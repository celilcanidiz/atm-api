package com.atm.dto;

import com.atm.model.TransactionType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.math.BigDecimal;
import java.util.Set;

@Builder
public record TransactionDto(

        @NotNull
        @NotEmpty
        Set<TransactionType> type,
        @NotNull
        @NotEmpty
        @Min(value = 0, message = "Amount must be greater than 0")
        BigDecimal amount) {
}
