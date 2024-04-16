package com.maurlox21.cryptoalert.component;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.maurlox21.cryptoalert.component.Dto.CoinMarket.CoinMarketResponse;

@Component
public class RestCoinmarket {

    private final RestTemplate restTemplate = new RestTemplate();
    
    @Value("${environment.api.coinmarket.key}")
    private String API_KEY;

    private final String API_KEY_TYPE = "X-CMC_PRO_API_KEY";

    private final String API_URL_ORIGIN = "https://pro-api.coinmarketcap.com";


    public CoinMarketResponse getLatestQuoteOfCryptocurrency(String symbol){

        HttpHeaders headers =  new HttpHeaders();
        headers.add(API_KEY_TYPE, API_KEY);

        HttpEntity<String> request = new HttpEntity<>(headers);

        String url = API_URL_ORIGIN + "/v2/cryptocurrency/quotes/latest?symbol=" + symbol + "&convert=BRL";
        ResponseEntity<CoinMarketResponse> response = restTemplate.exchange(url, HttpMethod.GET, request, new ParameterizedTypeReference<CoinMarketResponse>() {});

        return response.getBody();
    }
}
