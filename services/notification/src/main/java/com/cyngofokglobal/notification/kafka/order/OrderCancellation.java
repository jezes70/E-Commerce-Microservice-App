package com.cyngofokglobal.notification.kafka.order;

import com.cyngofokglobal.notification.kafka.payment.PaymentMethod;

import java.math.BigDecimal;
import java.util.List;

public record OrderCancellation(
        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        Customer customer,
        ReFundedAmount refundedAmount,
        List<Product> products
) {
}
