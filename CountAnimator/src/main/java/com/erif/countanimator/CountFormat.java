package com.erif.countanimator;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Currency;

public class CountFormat {

    public static NumberFormat Currency(String currencyCode, int fractionDigits) {
        try {
            NumberFormat format = NumberFormat.getCurrencyInstance();
            format.setMaximumFractionDigits(Math.max(fractionDigits, 0));
            format.setCurrency(Currency.getInstance(currencyCode));
            return format;
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    public static NumberFormat Currency(String currencyCode) {
        return Currency(currencyCode, 0);
    }

    public static NumberFormat Decimal(String decimalFormat) {
        try {
            return new DecimalFormat(decimalFormat);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

}
