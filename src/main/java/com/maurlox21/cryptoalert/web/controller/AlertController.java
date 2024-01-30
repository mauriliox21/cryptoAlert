package com.maurlox21.cryptoalert.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maurlox21.cryptoalert.entity.Alert;
import com.maurlox21.cryptoalert.entity.User;
import com.maurlox21.cryptoalert.jwt.JwtUserDetails;
import com.maurlox21.cryptoalert.repostory.projection.AlertProjection;
import com.maurlox21.cryptoalert.service.AlertSevice;
import com.maurlox21.cryptoalert.web.dto.AlertCreateDto;
import com.maurlox21.cryptoalert.web.dto.AlertResponseDto;
import com.maurlox21.cryptoalert.web.dto.PageDto;
import com.maurlox21.cryptoalert.web.dto.mapper.AlertMapper;
import com.maurlox21.cryptoalert.web.dto.mapper.PageMapper;

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

    @GetMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<PageDto> getAlertOfUser(@AuthenticationPrincipal JwtUserDetails userDetails, @PageableDefault Pageable pageable){
        
        Page<AlertProjection> alerts = this.alertSevice.getAlertsByIdUser(userDetails.getId(), pageable);
        
        return ResponseEntity.ok(PageMapper.toDto(alerts));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<AlertResponseDto> getAlertById(@PathVariable Long id, @AuthenticationPrincipal JwtUserDetails userDetails){

        Alert alert = this.alertSevice.getAlertUserbyId(id, userDetails.getId());

        return ResponseEntity.ok(AlertMapper.toDto(alert));
    }
}
