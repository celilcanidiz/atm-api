package com.atm.dto;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
public record AccountDto(
        @NotNull
        @NotEmpty
        Long user_id,
        @NotNull
        @NotEmpty
        @Min(value = 0, message = "Amount must be greater than 0")
        BigDecimal balance,
        LocalDateTime created_at,
        LocalDateTime updated_at
) {
}
