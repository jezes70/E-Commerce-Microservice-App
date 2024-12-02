package com.cyngofokglobal.payment.hander;

import java.util.Map;

public record CustomErrorResponse(
        Map<String, String> errors
) {}
