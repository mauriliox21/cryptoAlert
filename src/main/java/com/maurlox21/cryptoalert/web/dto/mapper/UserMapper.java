package com.maurlox21.cryptoalert.web.dto.mapper;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

import com.maurlox21.cryptoalert.entity.User;
import com.maurlox21.cryptoalert.web.dto.UserCreateDto;
import com.maurlox21.cryptoalert.web.dto.UserResponseDto;

public class UserMapper {
    
    public static User toEntity(UserCreateDto userDto){
        return new ModelMapper().map(userDto, User.class);
    }

    public static UserResponseDto toDto(User user) {
        
        String role = user.getTxRole().replace("ROLE_", "");
        PropertyMap<User, UserResponseDto> props = new PropertyMap<User,UserResponseDto>() {
            @Override
            protected void configure() {
                map().setTxRole(role);
            }
        };

        ModelMapper mapper = new ModelMapper();
        mapper.addMappings(props);
        
        return mapper.map(user, UserResponseDto.class);
    }
}