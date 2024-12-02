package com.cyngofokglobal.order.orderline;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderLineRepository extends JpaRepository<OrderLine, Integer> {
    List<OrderLine>findAllByOrder_Id(Integer orderId);

    List<OrderLine> findByOrder_Id(Integer orderId);

}
