package com.cyngofokglobal.order.hander;

import java.util.Map;

public record CustomErrorResponse(
        Map<String, String> errors
) {}
