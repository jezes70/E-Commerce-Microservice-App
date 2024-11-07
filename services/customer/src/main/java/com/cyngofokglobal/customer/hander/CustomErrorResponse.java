package com.cyngofokglobal.customer.hander;

import java.util.Map;

public record CustomErrorResponse(
        Map<String, String> errors
) {}
