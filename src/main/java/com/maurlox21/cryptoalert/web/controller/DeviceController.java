package com.maurlox21.cryptoalert.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maurlox21.cryptoalert.jwt.JwtUserDetails;
import com.maurlox21.cryptoalert.service.DeviceService;
import com.maurlox21.cryptoalert.web.dto.DeviceCreateDto;
import com.maurlox21.cryptoalert.web.dto.mapper.DeviceMapper;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/devices")
public class DeviceController {
    
    @Autowired
    private DeviceService deviceService;

    @PostMapping()
    public ResponseEntity<?> create (@RequestBody @Valid DeviceCreateDto device, @AuthenticationPrincipal JwtUserDetails userDetails){

        this.deviceService.create(DeviceMapper.toEntity(device, userDetails.getId()));

        return ResponseEntity.noContent().build();
    }
}
