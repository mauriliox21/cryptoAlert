package com.maurlox21.cryptoalert.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.maurlox21.cryptoalert.entity.Alert;
import com.maurlox21.cryptoalert.entity.Cryptocurrency;
import com.maurlox21.cryptoalert.entity.User;
import com.maurlox21.cryptoalert.exception.BusinessRuleException;
import com.maurlox21.cryptoalert.exception.EntityNotFoundException;
import com.maurlox21.cryptoalert.repostory.AlertRepository;
import com.maurlox21.cryptoalert.repostory.projection.AlertProjection;

public class AlertServiceTest {
    @Mock
    private AlertRepository alertRepository;
    
    @Mock
    private CryptocurrencyService cryptocurrencyService;

    @Autowired
    @InjectMocks
    private AlertSevice alertSevice;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should create alert successfuly when data is correct")
    void createCase1() {
        //Arrange
        Cryptocurrency cryptocurrency = new Cryptocurrency(1L, "Bitcoin", "BTC", "bitcoin.png", null);
        User user = new User(1L, "João", "123456", "joao@gmail.com", "ROLE_USER", null, null);
        Alert alert = new Alert(1L, 100.5, "TO_UP", new Cryptocurrency(1L, "", "", "", null), user);

        when(this.cryptocurrencyService.getById(1L)).thenReturn(cryptocurrency);
        when(this.alertRepository.save(alert)).thenReturn(alert);

        //Act
        Alert newAlert = this.alertSevice.create(alert);

        //Assert
        verify(this.cryptocurrencyService, times(1)).getById(any());
        verify(this.alertRepository, times(1)).save(any());
        assertThat(newAlert.getCryptocurrency()).isEqualTo(cryptocurrency);
    }

    @Test
    @DisplayName("Should throws exeption if cryptocurrency does not exists")
    void createCase2() {
        //Arrange
        User user = new User(1L, "João", "123456", "joao@gmail.com", "ROLE_USER", null, null);
        Alert alert = new Alert(1L, 100.5, "TO_UP", new Cryptocurrency(1L, "", "", "", null), user);

        when(this.cryptocurrencyService.getById(1L)).thenThrow(new RuntimeException("Cryptocurrency not found"));
        when(this.alertRepository.save(alert)).thenReturn(alert);

        //Act
        BusinessRuleException ex = Assertions.assertThrows(BusinessRuleException.class, () -> {
            Alert newAlert = this.alertSevice.create(alert);
        });

        //Assert
        verify(this.cryptocurrencyService, times(1)).getById(any());
        Assertions.assertEquals("Cryptocurrency not found", ex.getMessage());
    }

    @Test
    @DisplayName("Should returns alert successfuly when exists an alert with alert id and user id")
    void getAlertUserbyIdCase1() {
        //Arrange
        Long userId = 1L;
        Long alertId = 1L;
        Optional<Alert> opt = Optional.of(new Alert(alertId, 100.5, "TO_UP", null, new User(userId, "João", "123456", "joao@gmail.com", "ROLE_USER", null, null)));
        
        when(this.alertRepository.findByIdAndIdUser(alertId, userId)).thenReturn(opt);

        //Act
        Alert alert = this.alertSevice.getAlertUserbyId(alertId, userId);

        //Assert
        verify(this.alertRepository, times(1)).findByIdAndIdUser(any(), any());
        assertThat(alert).isEqualTo(opt.get());
        assertThat(alert.getId()).isEqualTo(alertId);
    }

    @Test
    @DisplayName("Should throws exeption if not exists an alert with alert id and user id")
    void getAlertUserbyIdCase2() {
        //Arrange
        Long userId = 1L;
        Long alertId = 1L;
        Optional<Alert> opt = Optional.empty();
        
        when(this.alertRepository.findByIdAndIdUser(alertId, userId)).thenReturn(opt);

        //Act
        EntityNotFoundException ex = Assertions.assertThrows(EntityNotFoundException.class, () -> {
            Alert alert = this.alertSevice.getAlertUserbyId(alertId, userId);
        });

        //Assert
        verify(this.alertRepository, times(1)).findByIdAndIdUser(any(), any());
        Assertions.assertEquals("Not found alert by id " + alertId, ex.getMessage());
    }

    @Test
    @DisplayName("Should create alert successfuly when data is correct")
    void alterCase1() {
        //Arrange
        Long userId = 1L;
        Long alertId = 1L;
        User user = new User(userId, "João", "123456", "joao@gmail.com", "ROLE_USER", null, null);
        Cryptocurrency cryptocurrency = Mockito.spy(new Cryptocurrency(1L, "Bitcoin", "BTC", "bitcoin.png", null));

        Alert dataForUpdate = new Alert(null, 100.5, "TO_UP", new Cryptocurrency(1L, null, null, null, null), user);
        Alert existentAlert = new Alert(alertId, 50.0, "TO_DOWN", cryptocurrency, user); 
        Optional<Alert> opt = Optional.of(existentAlert);
        
        when(this.alertRepository.findByIdAndIdUser(alertId, userId)).thenReturn(opt);
        when(this.cryptocurrencyService.getById(1L)).thenReturn(cryptocurrency);
        when(this.alertRepository.save(any())).thenReturn(existentAlert);

        //Act
        this.alertSevice.alter(alertId, dataForUpdate);

        //Assert
        verify(this.alertRepository, times(1)).findByIdAndIdUser(any(), any());
        verify(this.cryptocurrencyService, times(1)).getById(any());
        verify(this.alertRepository, times(1)).save(any());
        //check if the alert has changed
        assertThat(existentAlert.getTpAlert()).isEqualTo(dataForUpdate.getTpAlert());
        assertThat(existentAlert.getNrTargetValue()).isEqualTo(dataForUpdate.getNrTargetValue());
        assertThat(existentAlert.getCryptocurrency()).isEqualTo(cryptocurrency);

    }

    @Test
    @DisplayName("Should throws exeption if alert does not exists")
    void alterCase2() {
        //Arrange
        Long userId = 1L;
        Long alertId = 999L;
        User user = new User(userId, "João", "123456", "joao@gmail.com", "ROLE_USER", null, null);
        Cryptocurrency cryptocurrency = Mockito.spy(new Cryptocurrency(1L, "Bitcoin", "BTC", "bitcoin.png", null));

        Alert dataForUpdate = new Alert(null, 100.5, "TO_UP", new Cryptocurrency(1L, null, null, null, null), user);
        Alert existentAlert = new Alert(alertId, 50.0, "TO_DOWN", cryptocurrency, user); 
        Optional<Alert> opt = Optional.empty();
        
        when(this.alertRepository.findByIdAndIdUser(alertId, userId)).thenReturn(opt);
        when(this.cryptocurrencyService.getById(1L)).thenReturn(cryptocurrency);
        when(this.alertRepository.save(any())).thenReturn(existentAlert);

        //Act
        EntityNotFoundException ex = Assertions.assertThrows(EntityNotFoundException.class, () -> {
            this.alertSevice.alter(alertId, dataForUpdate);
        });

        //Assert
        verify(this.alertRepository, times(1)).findByIdAndIdUser(any(), any());
        Assertions.assertEquals("Not found alert by id " + alertId, ex.getMessage());
    }

    @Test
    @DisplayName("Should throws exeption if cryptocurrency does not exists")
    void alterCase3() {
        //Arrange
        Long userId = 1L;
        Long alertId = 1L;
        User user = new User(userId, "João", "123456", "joao@gmail.com", "ROLE_USER", null, null);
        Cryptocurrency cryptocurrency = Mockito.spy(new Cryptocurrency(999L, "Bitcoin", "BTC", "bitcoin.png", null));

        Alert dataForUpdate = new Alert(null, 100.5, "TO_UP", new Cryptocurrency(1L, null, null, null, null), user);
        Alert existentAlert = new Alert(alertId, 50.0, "TO_DOWN", cryptocurrency, user); 
        Optional<Alert> opt = Optional.of(existentAlert);
        
        when(this.alertRepository.findByIdAndIdUser(alertId, userId)).thenReturn(opt);
        when(this.cryptocurrencyService.getById(1L)).thenThrow(new RuntimeException("Cryptocurrency not found"));
        when(this.alertRepository.save(any())).thenReturn(existentAlert);

        //Act
        BusinessRuleException ex = Assertions.assertThrows(BusinessRuleException.class, () -> {
            this.alertSevice.alter(alertId, dataForUpdate);
        });

        //Assert
        verify(this.cryptocurrencyService, times(1)).getById(any());
        Assertions.assertEquals("Cryptocurrency not found", ex.getMessage());
    }

    @Test
    @DisplayName("Should delete alert successfuly when data is correct")
    void deleteCase1(){
        //Arrange
        Long userId = 1L;
        Long alertId = 1L;
        Alert alert = new Alert(alertId, 100.5, "TO_UP", new Cryptocurrency(999L, "Bitcoin", "BTC", "bitcoin.png", null), null);
        Optional<Alert> opt = Optional.of(alert);

        when(this.alertRepository.findByIdAndIdUser(alertId, userId)).thenReturn(opt);

        //Act
        this.alertSevice.delete(alertId, userId);

        verify(this.alertRepository, times(1)).findByIdAndIdUser(any(), any());
        verify(this.alertRepository, times(1)).delete(any());
    }

    @Test
    @DisplayName("Should throws exeption if alert does not exists")
    void deleteCase2(){
        //Arrange
        Long userId = 1L;
        Long alertId = 1L;
        Optional<Alert> opt = Optional.empty();

        when(this.alertRepository.findByIdAndIdUser(alertId, userId)).thenReturn(opt);

        //Act
        EntityNotFoundException ex = Assertions.assertThrows(EntityNotFoundException.class, () -> {
            this.alertSevice.delete(alertId, userId);
        });
    
        verify(this.alertRepository, times(1)).findByIdAndIdUser(any(), any());
        Assertions.assertEquals("Not found alert by id " + alertId, ex.getMessage());
    }

}
