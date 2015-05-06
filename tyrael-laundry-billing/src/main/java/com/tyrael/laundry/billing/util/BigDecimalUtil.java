package com.tyrael.laundry.billing.util;

import java.math.BigDecimal;

public abstract class BigDecimalUtil {

    public static BigDecimal zeroIfNull(BigDecimal a) {
        return null == a ? BigDecimal.ZERO : a;
    }
    public static boolean gt(BigDecimal a, BigDecimal b) {
        return zeroIfNull(a).compareTo(zeroIfNull(b)) > 0;
    }
    public static boolean lt(BigDecimal a, BigDecimal b) {
        return zeroIfNull(a).compareTo(zeroIfNull(b)) < 0;
    }

}
