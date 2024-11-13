package com.cyngofokglobal.order.order;

import com.cyngofokglobal.order.customer.CustomerClient;
import com.cyngofokglobal.order.exception.BusinessException;
import com.cyngofokglobal.order.orderline.OrderLineRequest;
import com.cyngofokglobal.order.orderline.OrderLineService;
import com.cyngofokglobal.order.product.ProductClient;
import com.cyngofokglobal.order.product.PurchaseRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OrderRepository repository;
    private final OrderMapper mapper;
    private final OrderLineService orderLineService;
    public Integer createdOrder(OrderRequest request) {
        var customer = this.customerClient.findCustomerById(request.customerId())
                .orElseThrow(() -> new BusinessException("Cannot create order:: No Customer exists with the provided ID"));

        this.productClient.purchaseProducts(request.products());

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


        return null;
    }
}
