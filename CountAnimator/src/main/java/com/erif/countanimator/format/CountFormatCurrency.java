package com.erif.countanimator.format;

import androidx.annotation.NonNull;

import java.text.NumberFormat;
import java.util.Currency;

public class CountFormatCurrency extends CountFormat {

    public CountFormatCurrency(@NonNull String currencyCode, int fractionDigits) {
        setFormat(parse(currencyCode, fractionDigits));
    }

    public CountFormatCurrency(@NonNull String currencyCode) {
        setFormat(parse(currencyCode, 0));
    }

    private NumberFormat parse(String currencyCode, int fractionDigits) {
        NumberFormat format = null;
        try {
            format = NumberFormat.getCurrencyInstance();
            format.setMaximumFractionDigits(Math.max(fractionDigits, 0));
            format.setCurrency(Currency.getInstance(currencyCode));
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return format;
    }

}
