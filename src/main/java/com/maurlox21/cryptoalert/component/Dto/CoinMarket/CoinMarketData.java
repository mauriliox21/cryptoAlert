package com.maurlox21.cryptoalert.component.Dto.CoinMarket;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CoinMarketData {
    
    @JsonAlias("BTC")
    private ArrayList<CoinMarketCryptocurrency> criptocurrency; 
}
