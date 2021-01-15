package com.hakansander.currency.model.CurrencyResponse.realTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ScrappedCurrency {
    private String base;
    private String target;
    private String rate;
}
