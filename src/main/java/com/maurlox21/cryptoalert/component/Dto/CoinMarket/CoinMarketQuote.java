package com.maurlox21.cryptoalert.component.Dto.CoinMarket;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CoinMarketQuote {
    
    @JsonAlias("USD")
    public CoinMarketCoin coin;    
}
