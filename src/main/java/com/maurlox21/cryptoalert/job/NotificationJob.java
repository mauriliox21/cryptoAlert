package com.maurlox21.cryptoalert.job;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.maurlox21.cryptoalert.component.RestCoinmarket;
import com.maurlox21.cryptoalert.component.Dto.CoinMarket.CoinMarketResponse;
import com.maurlox21.cryptoalert.entity.Alert;
import com.maurlox21.cryptoalert.entity.Cryptocurrency;
import com.maurlox21.cryptoalert.service.AlertSevice;
import com.maurlox21.cryptoalert.service.CryptocurrencyService;

@Component
public class NotificationJob {
    
    private CryptocurrencyService cryptocurrencyService;
    private AlertSevice alertSevice;
    private RestCoinmarket restCoinmarket;

    @Autowired
    public NotificationJob (CryptocurrencyService cryptocurrencyService, AlertSevice alertSevice, RestCoinmarket restCoinmarket){
        this.cryptocurrencyService = cryptocurrencyService;
        this.alertSevice = alertSevice;
        this.restCoinmarket = restCoinmarket;
    }

    //@Scheduled(fixedDelay = (1000 * 60 * 60))
    public void notificate() {
        List<Cryptocurrency> cryptocurrencies = this.cryptocurrencyService.findAll();

        for (Cryptocurrency cryptocurrency : cryptocurrencies) {

            CoinMarketResponse response = this.restCoinmarket.getLatestQuoteOfCryptocurrency(cryptocurrency.getTxSymbol());

            Page<Alert> alerts = this.alertSevice.getAlertsByIdCryptocurrenncy(cryptocurrency.getId(), PageRequest.of(0, 1000));

            Double price = response.getData().getCriptocurrency().get(0).getQuote().getCoin().getPrice();

            this.processAlerts(price, alerts.getContent());
        }
    }

    private void processAlerts(Double currentPrice, List<Alert> alerts){

        for (Alert alert : alerts) {
            
            if(alert.getTpAlert().toUpperCase().equals(Alert.TypeAlert.TO_UP.name().toUpperCase()) && currentPrice >= alert.getNrTargetValue()){
                System.out.println("Alert by Id:'" + alert.getId() + "' is done");
            }

            else if(alert.getTpAlert().toUpperCase().equals(Alert.TypeAlert.TO_DOWN.name().toUpperCase()) && currentPrice <= alert.getNrTargetValue()){
                System.out.println("Alert by Id:'" + alert.getId() + "' is done");
            }
            
        }
    }
}
