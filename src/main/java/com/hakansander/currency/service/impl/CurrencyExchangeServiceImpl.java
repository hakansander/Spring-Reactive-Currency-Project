package com.hakansander.currency.service.impl;

import com.hakansander.currency.mapper.CurrencyResponseMapper;
import com.hakansander.currency.model.CurrencyResponse.CurrencyResponse;
import com.hakansander.currency.model.CurrencyResponse.CurrencyResponseDto;
import com.hakansander.currency.service.CurrencyExchangeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@Slf4j
public class CurrencyExchangeServiceImpl implements CurrencyExchangeService {

    private final static String SCHEME = "https";
    private final static String HOST = "api.exchangeratesapi.io";
    private final static String LATEST_PATH = "/latest";
    private final static String DATE_PATH = "/";
    private final static String DATE_RANGE_PATH = "/history";
    private final static String QUERY_BASE_CURRENCY = "base={base}";
    private final static String QUERY_TARGET_CURRENCIES = "symbols={targetCurrencies}";
    private final static String QUERY_START_DATE = "start_at={startDate}";
    private final static String QUERY_END_DATE = "end_at={endDate}";

    public CurrencyResponseDto getCurrencyExchangeValues(String baseCurrency) {
        log.info("[getCurrencyExchange for baseCurrency] Method is called :: baseCurrency={}", baseCurrency);

        UriComponents uriComponents = UriComponentsBuilder.newInstance().
                scheme(SCHEME).host(HOST).
                path(LATEST_PATH).
                query(QUERY_BASE_CURRENCY).
                buildAndExpand(baseCurrency);

        CurrencyResponse response = currencyServiceCall(uriComponents, baseCurrency);

        return CurrencyResponseMapper.mapCurrencyResponse(response);
    }

    private CurrencyResponse currencyServiceCall(UriComponents uriComponents, String baseCurrency) {
        RestTemplate restTemplate = new RestTemplate();

        CurrencyResponse response = null;

        response = restTemplate.getForObject(uriComponents.toString(), CurrencyResponse.class);

        return response;
    }
}
