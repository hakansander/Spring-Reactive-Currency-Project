package com.hakansander.currency.util;

import com.hakansander.currency.model.CurrencyResponse.realTime.ScrappedCurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CurrencyUtils {

    public static List<ScrappedCurrency> getRandomCurrencyList() {
        final long MAX = 10L;
        final long MIN = 1L;

        ScrappedCurrency currency1 = new ScrappedCurrency("TRY", "USD", getRandomFloat(MAX, MIN).toString());

        ScrappedCurrency currency2 = new ScrappedCurrency("TRY", "EUR", getRandomFloat(MAX, MIN).toString());

        List<ScrappedCurrency> scrappedCurrencyList = new ArrayList<>();

        scrappedCurrencyList.add(currency1);
        scrappedCurrencyList.add(currency2);

        return scrappedCurrencyList;
    }

    public static Float getRandomFloat(long max, long min) {
        return min + new Random().nextFloat() * (max - min);
    }
}
