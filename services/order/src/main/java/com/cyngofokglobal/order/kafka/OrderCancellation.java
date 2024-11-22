package com.cyngofokglobal.order.kafka;

import com.cyngofokglobal.order.customer.CustomerResponse;
import com.cyngofokglobal.order.order.PaymentMethod;
import com.cyngofokglobal.order.product.PurchaseResponse;

import java.math.BigDecimal;
import java.util.List;

public record OrderCancellation(

        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        CustomerResponse customer,
        List<PurchaseResponse> products
) {
}
