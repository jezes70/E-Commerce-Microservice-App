package com.cyngofokglobal.order.orderline;

import com.cyngofokglobal.order.order.Order;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class OrderLine {

    @Id
    @GeneratedValue
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
    private Integer productId;
    private double quantity;

    public Integer getOrderId() {
        return this.order != null ? this.order.getId() : null;
    }
}

