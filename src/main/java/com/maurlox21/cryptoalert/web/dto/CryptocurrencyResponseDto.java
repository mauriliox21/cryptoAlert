package com.maurlox21.cryptoalert.web.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CryptocurrencyResponseDto {
    
    private Long id;

    private String nmCryptocurrency;

    private String txSymbol;

    private String txPathIcon;

}
