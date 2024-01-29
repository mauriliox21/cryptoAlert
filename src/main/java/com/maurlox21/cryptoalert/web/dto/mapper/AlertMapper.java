package com.maurlox21.cryptoalert.web.dto.mapper;

import org.modelmapper.ModelMapper;

import com.maurlox21.cryptoalert.entity.Alert;
import com.maurlox21.cryptoalert.web.dto.AlertCreateDto;
import com.maurlox21.cryptoalert.web.dto.AlertResponseDto;

public class AlertMapper {
    
    public static Alert toEntity(AlertCreateDto alertDto){
        
        return new ModelMapper().map(alertDto, Alert.class);
    }

    public static AlertResponseDto toDto(Alert alert){

        return new ModelMapper().map(alert, AlertResponseDto.class);
    }
}
