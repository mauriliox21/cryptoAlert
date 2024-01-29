package com.maurlox21.cryptoalert.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.maurlox21.cryptoalert.entity.Alert;
import com.maurlox21.cryptoalert.repostory.AlertRepository;

@Service
public class AlertSevice {
    
    @Autowired
    private AlertRepository repository;

    @Transactional
    public Alert create (Alert alert){

        Alert alertCreated = this.repository.save(alert);

        return alertCreated;
    }
}
