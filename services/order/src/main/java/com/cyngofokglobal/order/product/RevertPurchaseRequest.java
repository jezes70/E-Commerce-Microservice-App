package com.cyngofokglobal.order.product;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record RevertPurchaseRequest(
        @NotNull(message = "Product ID should be present")
        Integer productId,

        @Positive(message = "Quantity should be positive")
        double quantity
) {


}
