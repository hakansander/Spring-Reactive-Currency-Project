package com.hakansander.currency.service;

import com.hakansander.currency.model.CurrencyResponse.CurrencyResponseDto;

public interface CurrencyExchangeService {
    CurrencyResponseDto getCurrencyExchangeValues(String baseCurrency);
}
