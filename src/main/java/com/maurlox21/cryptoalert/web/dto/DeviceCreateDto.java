package com.maurlox21.cryptoalert.web.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeviceCreateDto {
    
    private String txNotificationToken;

    @NotBlank
    private String nmManufacturer;

    @NotBlank
    private String nmOs;

    @NotBlank
    private String txOsVersion;

    @NotBlank
    private String txDeviceType;
}
