package com.maurlox21.cryptoalert.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maurlox21.cryptoalert.entity.Alert;
import com.maurlox21.cryptoalert.entity.User;
import com.maurlox21.cryptoalert.jwt.JwtUserDetails;
import com.maurlox21.cryptoalert.service.AlertSevice;
import com.maurlox21.cryptoalert.web.dto.AlertCreateDto;
import com.maurlox21.cryptoalert.web.dto.AlertResponseDto;
import com.maurlox21.cryptoalert.web.dto.mapper.AlertMapper;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/alerts")
public class AlertController {
    
    @Autowired
    private AlertSevice alertSevice;

    @PostMapping()
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<AlertResponseDto> create(@RequestBody @Valid AlertCreateDto alertDto, @AuthenticationPrincipal JwtUserDetails userDetails){
        
        Alert alert = AlertMapper.toEntity(alertDto);

        User userOwner = new User();
        userOwner.setId(userDetails.getId());
        alert.setUser(userOwner);
        
        Alert alertCreated = this.alertSevice.create(alert);

        return ResponseEntity.status(HttpStatus.CREATED).body(AlertMapper.toDto(alertCreated));
    }
}
