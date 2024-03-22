package com.maurlox21.cryptoalert.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "tb_device")
public class Device {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_device")
    private Long id;

    @Column(name = "tx_notification_token")
    private String txNotificationToken;

    @Column(name = "nm_manufacturer")
    private String nmManufacturer;

    @Column(name = "nm_os")
    private String nmOs;

    @Column(name = "tx_os_version")
    private String txOsVersion;

    @Column(name = "tx_device_type")
    private String txDeviceType;
}
