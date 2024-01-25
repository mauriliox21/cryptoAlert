package com.maurlox21.cryptoalert.web.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CryptocurrencyCreateDto {
    
    @NotBlank
    private String nmCryptocurrency;

    @NotBlank
    private String txSymbol;

    @NotBlank
    private String txPathIcon;
}
