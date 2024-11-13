package com.cyngofokglobal.product.hander;

import java.util.Map;

public record CustomErrorResponse(
        Map<String, String> errors
) {}
