package com.hakansander.currency.model.CurrencyResponse;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "date",
        "base",
        "rate"
})
@AllArgsConstructor
@NoArgsConstructor
public class CurrencyResponse {
    @JsonProperty("date")
    private String date;
    @JsonProperty("base")
    private String base;
    @JsonProperty("rates")
    Map<String, Double> rates;
}