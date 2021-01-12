package com.hakansander.currency.model.CurrencyResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.SortedMap;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CurrencyResponseDto {
    boolean success;
    String responseMsg;
    String statusCode;
    String response;
    SortedMap<String, Object> data;
}
