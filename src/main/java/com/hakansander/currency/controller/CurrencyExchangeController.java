package com.hakansander.currency.controller;

import com.hakansander.currency.model.CurrencyResponse.CurrencyResponseDto;
import com.hakansander.currency.service.CurrencyExchangeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.time.Duration;

@RestController
@RequestMapping("/currency")
@CrossOrigin(origins = "*")
@Slf4j
public class CurrencyExchangeController {
    @Autowired
    CurrencyExchangeService currencyExchangeService;

    @GetMapping(value = "/{baseCurrency}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<CurrencyResponseDto> getCurrencyValues(@PathVariable String baseCurrency) {

        return Flux.interval(Duration.ofSeconds(20))
                .map(it -> currencyExchangeService.getCurrencyExchangeValues(baseCurrency))
                .doOnNext(it -> log.info("[getCurrencyValues] Method is returned :: it={}", it));


    }
}
