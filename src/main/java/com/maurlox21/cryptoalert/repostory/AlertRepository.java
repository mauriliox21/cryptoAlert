package com.maurlox21.cryptoalert.repostory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.maurlox21.cryptoalert.entity.Alert;

@Repository
public interface AlertRepository extends JpaRepository<Alert, Long> {
    
}
