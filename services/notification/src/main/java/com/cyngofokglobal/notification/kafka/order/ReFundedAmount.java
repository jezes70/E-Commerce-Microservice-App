package com.cyngofokglobal.notification.kafka.order;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public enum ReFundedAmount {

    FULL_REFUND(new BigDecimal("100.00")),
    PARTIAL_REFUND(new BigDecimal("50.00")),
    NO_REFUND(BigDecimal.ZERO);

    private final BigDecimal amount;

    ReFundedAmount(BigDecimal amount) {
        this.amount = amount;
    }

}
