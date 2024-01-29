package com.maurlox21.cryptoalert.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlertResponseDto {
    
    private Long id;

    private Double nrTargetValue;

    private CryptocurrencyResponseDto cryptocurrency;
}
