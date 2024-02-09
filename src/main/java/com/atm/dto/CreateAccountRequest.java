package com.atm.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record CreateAccountRequest(
    @NotNull
    @NotEmpty
    Long user_id,
    @NotNull(message = "Balance cannot be null")
    @NotEmpty(message = "Balance cannot be empty")
    @Min(value = 0, message = "Balance cannot be less than 0")
    BigDecimal balance

) {
}
