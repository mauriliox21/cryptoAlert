package com.maurlox21.cryptoalert.web.dto.mapper;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

import com.maurlox21.cryptoalert.entity.Device;
import com.maurlox21.cryptoalert.entity.User;
import com.maurlox21.cryptoalert.web.dto.DeviceCreateDto;

public class DeviceMapper {

    public static Device toEntity(DeviceCreateDto dto, Long idUser){
        
        User user = new User();
        user.setId(idUser);
        PropertyMap<DeviceCreateDto, Device> props = new PropertyMap<DeviceCreateDto, Device>() {
            @Override
            protected void configure() {
                map().setUser(user);
                map().setId(null);
            }
        };

        ModelMapper mapper = new ModelMapper();
        mapper.addMappings(props);
        
        return mapper.map(dto, Device.class);
    }
}
