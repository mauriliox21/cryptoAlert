package com.maurlox21.cryptoalert.web.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserCreateDto {
    
    @NotBlank
    private String nmUser;

    @NotBlank
    @Email(message = "Formato de e-mail está inváldo", regexp = "^[a-z0-9.+-]+@[a-z0-9.-]+\\.[a-z]{2,}$")
    private String txEmail;

    @NotBlank 
    private String txPassword;


}
