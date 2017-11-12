package com.transactrules.accounts.calculations;

import java.util.ArrayList;
import java.util.Arrays;

public class AccrualCalculation {
    public static Iterable<String> AccrualOptions() {
        return new ArrayList<>(Arrays.asList("Actual", "360", "365", "30/360"));
    }
}

