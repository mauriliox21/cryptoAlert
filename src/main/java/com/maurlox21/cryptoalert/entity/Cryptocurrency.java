package com.maurlox21.cryptoalert.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_cryptocurrency")
public class Cryptocurrency {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cryptocurrency")
    private Long id;

    @Column(name = "nm_cryptocurrency")
    private String nmCryptocurrency;

    @Column(name = "tx_symbol")
    private String txSymbol;

    @Column(name = "tx_path_icon")
    private String txPathIcon;

}