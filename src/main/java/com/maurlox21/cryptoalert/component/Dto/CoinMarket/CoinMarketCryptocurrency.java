package com.maurlox21.cryptoalert.component.Dto.CoinMarket;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CoinMarketCryptocurrency {
    
    public Long id;

    public String name;

    public String symbol;

    public CoinMarketQuote quote;
}
