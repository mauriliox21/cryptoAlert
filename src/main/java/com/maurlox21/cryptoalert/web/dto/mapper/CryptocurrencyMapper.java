package com.maurlox21.cryptoalert.web.dto.mapper;

import org.modelmapper.ModelMapper;

import com.maurlox21.cryptoalert.entity.Cryptocurrency;
import com.maurlox21.cryptoalert.web.dto.CryptocurrencyCreateDto;
import com.maurlox21.cryptoalert.web.dto.CryptocurrencyResponseDto;

public class CryptocurrencyMapper {
    public static Cryptocurrency toEntity(CryptocurrencyCreateDto cryptocurrencyDto){

        return new ModelMapper().map(cryptocurrencyDto, Cryptocurrency.class);
    }

    public static CryptocurrencyResponseDto toDto(Cryptocurrency cryptocurrency){
        
        return new ModelMapper().map(cryptocurrency, CryptocurrencyResponseDto.class);
    }
}
