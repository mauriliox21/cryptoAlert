package com.maurlox21.cryptoalert.web.dto.mapper;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

import com.maurlox21.cryptoalert.entity.Alert;
import com.maurlox21.cryptoalert.entity.User;
import com.maurlox21.cryptoalert.web.dto.AlertCreateDto;
import com.maurlox21.cryptoalert.web.dto.AlertResponseDto;
import com.maurlox21.cryptoalert.web.dto.AlertUpdateDto;

public class AlertMapper {

    public static Alert toEntity(AlertCreateDto alertDto, Long idUser){
        
        User user = new User();
        user.setId(idUser);
        PropertyMap<AlertCreateDto, Alert> props = new PropertyMap<AlertCreateDto, Alert>() {
            @Override
            protected void configure() {
                map().setUser(user);
            }
        };

        ModelMapper mapper = new ModelMapper();
        mapper.addMappings(props);
        
        return mapper.map(alertDto, Alert.class);
    }

    public static Alert toEntity(AlertUpdateDto alertDto, Long idUser){
        
        User user = new User();
        user.setId(idUser);
        PropertyMap<AlertUpdateDto, Alert> props = new PropertyMap<AlertUpdateDto, Alert>() {
            @Override
            protected void configure() {
                map().setUser(user);
                map().setId(null);
            }
        };

        ModelMapper mapper = new ModelMapper();
        mapper.addMappings(props);
        
        return mapper.map(alertDto, Alert.class);
    }

    public static AlertResponseDto toDto(Alert alert){

        return new ModelMapper().map(alert, AlertResponseDto.class);
    }
}
