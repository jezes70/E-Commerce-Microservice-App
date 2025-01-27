package com.cyngofokglobal.order.order;

import com.cyngofokglobal.order.customer.CustomerClient;
import com.cyngofokglobal.order.exception.BusinessException;
import com.cyngofokglobal.order.kafka.OrderCancellation;
import com.cyngofokglobal.order.kafka.OrderConfirmation;
import com.cyngofokglobal.order.kafka.OrderProducer;
import com.cyngofokglobal.order.orderline.OrderLineRequest;
import com.cyngofokglobal.order.orderline.OrderLineService;
import com.cyngofokglobal.order.payment.PaymentClient;
import com.cyngofokglobal.order.payment.PaymentRequest;
import com.cyngofokglobal.order.product.ProductClient;
import com.cyngofokglobal.order.product.PurchaseRequest;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OrderRepository repository;
    private final OrderMapper mapper;
    private final OrderLineService orderLineService;
    private final OrderProducer orderProducer;
    private  final PaymentClient paymentClient;
    public Integer createdOrder(OrderRequest request) {
        var customer = this.customerClient.findCustomerById(request.customerId())
                .orElseThrow(() -> new BusinessException("Cannot create order:: No Customer exists with the provided ID"));

        var purchaseProducts = this.productClient.purchaseProducts(request.products());

        var order = this.repository.save(mapper.toOrder(request));

        for(PurchaseRequest purchaseRequest: request.products()) {
            orderLineService.saveOrderLine(
                    new OrderLineRequest(
                            null,
                            order.getId(),
                            purchaseRequest.productId(),
                            purchaseRequest.quantity()
                    )

            );
        }
        var paymentRequest = new PaymentRequest(
                request.amount(),
                request.paymentMethod(),
                order.getId(),
                order.getReference(),
                customer
        );
        paymentClient.requestOrderPayment(paymentRequest);

        orderProducer.sendOrderConfirmation(
                new OrderConfirmation(
                        request.reference(),
                        request.amount(),
                        request.paymentMethod(),
                        customer,
                        purchaseProducts
                )
        );

        orderProducer.sendOrderCancellation(
                new OrderCancellation(
                        request.reference(),
                        request.amount(),
                        request.paymentMethod(),
                        customer,
                        purchaseProducts
                )
        );

        return order.getId();
    }
    public List<OrderResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::fromOrder)
                .collect(Collectors.toList());
    }
    public OrderResponse findById(Integer orderId) {
        return repository.findById(orderId)
                .map(mapper::fromOrder)
                .orElseThrow(() -> new EntityNotFoundException(format("No order found with the provided ID: %d", orderId)));
    }

    public void cancelOrder(Integer orderId) {
        var order = repository.findById(orderId)
                .orElseThrow(() -> new EntityNotFoundException(format("No order found with the provided ID: %d", orderId)));

        var orderLines = orderLineService.findOrderLinesByOrderId(orderId);

        orderLines.forEach(orderLine -> {
            productClient.revertProductPurchase(orderLine.productId(), orderLine.quantity());
        });

        paymentClient.requestOrderRefund(order.getId());

        order.setStatus("CANCELED");
        repository.save(order);
    }
}
