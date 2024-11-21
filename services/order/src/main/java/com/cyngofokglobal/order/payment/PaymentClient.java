package com.cyngofokglobal.order.payment;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = "product-service", url = "${application.config.payment-url}"
)
public interface PaymentClient {
    @PostMapping
    Integer requestOrderPayment(@RequestBody PaymentRequest request);
    @PostMapping("/refund/{order-id}")
    void requestOrderRefund(@PathVariable("order-id") Integer id);
}
