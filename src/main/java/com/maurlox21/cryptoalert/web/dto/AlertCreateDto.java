package com.maurlox21.cryptoalert.web.dto;

import com.maurlox21.cryptoalert.web.validation.inte.ValidateString;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
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

    @NotBlank
    @ValidateString(acceptedValues = {"TO_DOWN", "TO_UP"}, message = "'tpAlert' can only be 'TO_DOWN' or 'TO_UP'")
    private String tpAlert;

}
