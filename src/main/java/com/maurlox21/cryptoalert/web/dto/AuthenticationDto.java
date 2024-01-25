package com.maurlox21.cryptoalert.web.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationDto {
    
    @NotBlank
    private String txEmail;

    @NotBlank
    private String txPassword;

}
