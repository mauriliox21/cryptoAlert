package com.maurlox21.cryptoalert.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "tb_user")
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Long id;

    @Column(name = "nm_user")
    private String nmUser;

    @Column(name = "tx_password")
    private String txPassword;

    @Column(name = "tx_email")
    private String txEmail;

    @Column(name = "tx_role")
    private String txRole = Role.ROLE_USER.name();

    @OneToMany(mappedBy = "user")
    private List<Alert> alerts;

    @OneToMany(mappedBy = "user")
    private List<Device> devices;

    public enum Role {
        ROLE_ADMIN, ROLE_USER
    }

}
