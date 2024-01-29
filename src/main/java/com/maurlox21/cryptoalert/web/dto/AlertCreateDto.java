package com.maurlox21.cryptoalert.web.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlertCreateDto {

    @NotNull
    @Min(0)
    private Double nrTargetValue;
    
    @NotNull
    private Long idCryptocurrency;

}
