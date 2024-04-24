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
import com.maurlox21.cryptoalert.component.firebasemessaging.FirebaseMessagingService;
import com.maurlox21.cryptoalert.component.firebasemessaging.NotificationMessage;
import com.maurlox21.cryptoalert.entity.Alert;
import com.maurlox21.cryptoalert.entity.Cryptocurrency;
import com.maurlox21.cryptoalert.repostory.projection.DeviceProjection;
import com.maurlox21.cryptoalert.service.AlertSevice;
import com.maurlox21.cryptoalert.service.CryptocurrencyService;
import com.maurlox21.cryptoalert.service.DeviceService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class NotificationJob {
    
    private AlertSevice alertSevice;
    private CryptocurrencyService cryptocurrencyService;
    private DeviceService deviceService;
    private FirebaseMessagingService messagingService;
    private RestCoinmarket restCoinmarket;

    @Autowired
    public NotificationJob (CryptocurrencyService cryptocurrencyService, AlertSevice alertSevice, RestCoinmarket restCoinmarket, FirebaseMessagingService messagingService, DeviceService deviceService){
        this.cryptocurrencyService = cryptocurrencyService;
        this.alertSevice = alertSevice;
        this.restCoinmarket = restCoinmarket;
        this.messagingService = messagingService;
        this.deviceService = deviceService;
    }

    @Scheduled(fixedDelay = (1000 * 60 * 60))
    public void notificate() {
        List<Cryptocurrency> cryptocurrencies = this.cryptocurrencyService.findAll();

        for (Cryptocurrency cryptocurrency : cryptocurrencies) {

            CoinMarketResponse response = this.restCoinmarket.getLatestQuoteOfCryptocurrency(cryptocurrency.getTxSymbol());
            
            Double price = response.getData().getCriptocurrency().get(0).getQuote().getCoin().getPrice();

            Page<Alert> pageAlerts = this.alertSevice.getActiveAlertsByIdCryptocurrency(cryptocurrency.getId(), price, PageRequest.of(0, 1000));

            this.processAlerts(pageAlerts.getContent());
        }
    }

    private void processAlerts(List<Alert> alerts){

        for (Alert alert : alerts) {
            log.info("Alert by Id:'" + alert.getId() + "' is done");
            sendNotification(alert);
        }
    }

    private void sendNotification(Alert alert){
        Long idUser = alert.getUser().getId();

        List<DeviceProjection> devices = this.deviceService.getDevicesByIdUser(idUser);

        String title = alert.getCryptocurrency().getNmCryptocurrency() + 
            (alert.getTpAlert().equalsIgnoreCase(Alert.TypeAlert.TO_DOWN.name()) ? " caiu" : " subiu") +
            " para o valor que você esperava!";

        String body = "Seu alerta para a criptomoeda " + alert.getCryptocurrency().getNmCryptocurrency() +
                    " no valor de R$ " + alert.getNrTargetValue() + 
                    " foi atingido. Está na hora de agir.";
        

        for(DeviceProjection device : devices){
            if(device.getTxNotificationToken() != null && !device.getTxNotificationToken().isBlank())
            {
                try{
                    this.messagingService.SendNotificationByToken(NotificationMessage
                        .builder()
                        .title(title)
                        .body(body)
                        .recipientToken(device.getTxNotificationToken())
                        .build()
                    );
    
                    this.alertSevice.updateStateOfSending(alert);
                }
                catch(Exception ex){
                    log.info("Error sending notification to device id: " + device.getId() + " ------------------------------");
                }
            }
        }
        
    }
}
