package com.maurlox21.cryptoalert.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maurlox21.cryptoalert.entity.Cryptocurrency;
import com.maurlox21.cryptoalert.service.CryptocurrencyService;
import com.maurlox21.cryptoalert.web.dto.CryptocurrencyCreateDto;
import com.maurlox21.cryptoalert.web.dto.CryptocurrencyResponseDto;
import com.maurlox21.cryptoalert.web.dto.mapper.CryptocurrencyMapper;
import com.maurlox21.cryptoalert.web.dto.mapper.PageableMapper;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/cryptocurrency")
public class CryptocurrencyController {

    @Autowired
    private  CryptocurrencyService service;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CryptocurrencyResponseDto> create(@RequestBody @Valid CryptocurrencyCreateDto cryptocurrencyDto){

        Cryptocurrency cryptocurrency = this.service.create(CryptocurrencyMapper.toEntity(cryptocurrencyDto));

        return ResponseEntity.status(HttpStatus.CREATED).body(CryptocurrencyMapper.toDto(cryptocurrency));
    }
    
    @GetMapping
    public ResponseEntity<?> getAll(@PageableDefault Pageable pageable){

        Page<Cryptocurrency> criptocurrencies = this.service.getAll(pageable);

        return ResponseEntity.ok(PageableMapper.toDto(criptocurrencies));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CryptocurrencyResponseDto> getById(@PathVariable Long id){
        Cryptocurrency cryptocurrency = this.service.getById(id);
        return ResponseEntity.ok(CryptocurrencyMapper.toDto(cryptocurrency));
    }
}

