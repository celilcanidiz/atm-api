package com.atm.dto;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record TransactionRequestDto(
    String type,
    BigDecimal amount
) {

}
