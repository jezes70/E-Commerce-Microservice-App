package com.cyngofokglobal.order.payment;

import com.cyngofokglobal.order.customer.CustomerResponse;
import com.cyngofokglobal.order.order.PaymentMethod;

import java.math.BigDecimal;

public record PaymentRequest(
        BigDecimal amount,
        PaymentMethod paymentMethod,
        Integer orderId,
        String orderReference,
        CustomerResponse customer
) {
}
