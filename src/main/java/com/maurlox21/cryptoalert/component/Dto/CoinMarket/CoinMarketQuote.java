package com.maurlox21.cryptoalert.component.Dto.CoinMarket;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CoinMarketQuote {
    
    @JsonAlias("BRL")
    private CoinMarketCoin coin;    
}
