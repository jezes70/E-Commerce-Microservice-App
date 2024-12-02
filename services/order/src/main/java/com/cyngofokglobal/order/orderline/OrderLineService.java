package com.cyngofokglobal.order.orderline;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderLineService {

    private final OrderLineMapper mapper;
    private final OrderLineRepository repository;
    public Integer saveOrderLine(OrderLineRequest request) {
        var order = mapper.toOrderLine(request);
        return repository.save(order).getId();

    }
    public List<OrderLineResponse> findAllByOrderId(Integer orderId) {
         return repository.findAllByOrder_Id(orderId)
                 .stream()
                 .map(mapper::toOrderLineResponse)
                 .collect(Collectors.toList());
    }

    public List<OrderLineResponse> findOrderLinesByOrderId(Integer orderId) {
        var orderLines = repository.findByOrder_Id(orderId);

        return orderLines.stream()
                .map(mapper::fromOrderLine)
                .collect(Collectors.toList());
    }
}
