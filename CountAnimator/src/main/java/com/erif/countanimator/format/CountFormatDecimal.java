package com.erif.countanimator.format;

import androidx.annotation.NonNull;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class CountFormatDecimal extends CountFormat {

    public CountFormatDecimal(@NonNull String pattern) {
        DecimalFormat mFormat;
        try {
            mFormat = new DecimalFormat(pattern, DecimalFormatSymbols.getInstance(Locale.getDefault()));
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            mFormat = null;
        }
        setFormat(mFormat);
    }

}
