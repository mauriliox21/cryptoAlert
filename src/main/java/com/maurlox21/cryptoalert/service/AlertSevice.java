package com.maurlox21.cryptoalert.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.maurlox21.cryptoalert.entity.Alert;
import com.maurlox21.cryptoalert.exception.EntityNotFoundException;
import com.maurlox21.cryptoalert.repostory.AlertRepository;
import com.maurlox21.cryptoalert.repostory.projection.AlertProjection;

@Service
public class AlertSevice {
    
    @Autowired
    private AlertRepository repository;

    @Transactional
    public Alert create (Alert alert){

        Alert alertCreated = this.repository.save(alert);

        return alertCreated;
    }

    @Transactional(readOnly = true)
    public Page<AlertProjection> getAlertsByIdUser(Long idUser, Pageable pageable){

        return this.repository.findByIdUser(idUser, pageable);
    }

    public Alert getAlertUserbyId(Long idAlert, Long idUser) {
        
        Optional<Alert> optAlert = this.repository.findByIdAndIdUser(idAlert, idUser);

        if(optAlert.isEmpty())
            throw new EntityNotFoundException("Not found alert by id " + idAlert);

        return optAlert.get();
    }
}
