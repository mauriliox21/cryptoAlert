package com.maurlox21.cryptoalert.component.Dto.CoinMarket;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CoinMarketData {
    
    @JsonAlias("BTC")
    public ArrayList<CoinMarketCryptocurrency> criptocurrency; 
}
