package com.maurlox21.cryptoalert.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto {
    
    private Long id;

    private String nmUser;

    private String txEmail;

    private String txRole;
}
