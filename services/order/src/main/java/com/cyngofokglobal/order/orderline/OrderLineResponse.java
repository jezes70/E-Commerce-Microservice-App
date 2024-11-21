package com.cyngofokglobal.order.orderline;

public record OrderLineResponse(
        Integer id,
        double quantity,
        Integer productId
) {

    public Integer getProductId() {
        return productId;
    }
    public double getQuantity() {
        return quantity;
    }
}
