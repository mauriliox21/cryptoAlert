package com.maurlox21.cryptoalert.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import com.maurlox21.cryptoalert.entity.Alert;
import com.maurlox21.cryptoalert.repostory.AlertRepository;
import com.maurlox21.cryptoalert.repostory.projection.AlertProjection;

@DataJpaTest
@ActiveProfiles("test")
@Sql(scripts = "/sql/alerts/alerts-insert.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "/sql/alerts/alerts-delete.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class AlertRepositoryTest {

    @Autowired
    AlertRepository alertRepository;
    
    @Test
    @DisplayName("Should be able get alerts by user id")
    void findyIdUserCase1(){
        //Act
        Page<AlertProjection> result = this.alertRepository.findByIdUser(101L, PageRequest.of(0, 10));

        //Assert
        assertThat(result.isEmpty()).isFalse();

        AlertProjection alert = result.toList().get(0);
        assertThat(alert.getId()).isNotNull();
        assertThat(alert.getNrTargetValue()).isNotNull();
        assertThat(alert.getTpAlert()).isNotBlank();
        assertThat(alert.getCryptocurrency()).isNotNull();
    }

    @Test
    @DisplayName("should be able to returns empty list if user id not exists in DB")
    void findyIdUserCase2(){
        //Act
        Page<AlertProjection> result = this.alertRepository.findByIdUser(999L, PageRequest.of(0, 10));

        //Assert
        assertThat(result.isEmpty()).isTrue();
    }

    @Test
    @DisplayName("should be able to returns an alert if existis an alert with alert id and user id in DB")
    void findByIdAndIdUserCase1(){
        //Act
        Optional<Alert> result = this.alertRepository.findByIdAndIdUser(101L, 101L);

        //Assert
        assertThat(result.isPresent()).isTrue();
    }

    @Test
    @DisplayName("should be able to not return an alert if not found an alert with alert id and user id in the DB")
    void findByIdAndIdUserCase2(){
        //Act
        Optional<Alert> result = this.alertRepository.findByIdAndIdUser(999L, 999L);

        //Assert
        assertThat(result.isEmpty()).isTrue();
    }

    @Test
    @DisplayName("should be able to not return an alert if existis an alert with alert id, but not has user id in the DB")
    void findByIdAndIdUserCase3(){
        //Act
        Optional<Alert> result = this.alertRepository.findByIdAndIdUser(101L, 999L);

        //Assert
        assertThat(result.isEmpty()).isTrue();
    }

    @Test
    @DisplayName("should be able to not return an alert if existis an alert with user id, but not has alert id in the DB")
    void findByIdAndIdUserCase4(){
        //Act
        Optional<Alert> result = this.alertRepository.findByIdAndIdUser(999L, 101L);

        //Assert
        assertThat(result.isEmpty()).isTrue();
    }

    @Test
    @DisplayName("Should be able get alerts by cryptocurrency id")
    void findAllByIdCryptocurrencyCase1(){
        //Act
        Page<Alert> result = this.alertRepository.getActiveAlertsByIdCryptocurrency(101L, PageRequest.of(0, 10));

        //Assert
        assertThat(result.isEmpty()).isFalse();
    }

    @Test
    @DisplayName("should be able to returns empty list if cryptocurrency id not exists in DB")
    void findAllByIdCryptocurrencyCase2(){
        //Act
        Page<Alert> result = this.alertRepository.getActiveAlertsByIdCryptocurrency(999L, PageRequest.of(0, 10));

        //Assert
        assertThat(result.isEmpty()).isTrue();
    }
}
