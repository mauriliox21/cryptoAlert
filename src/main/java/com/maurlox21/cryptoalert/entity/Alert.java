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
@Table(name = "tb_alert")
public class Alert {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_alert")
    private Long id;

    @Column(name = "nr_target_value")
    private Double nrTargetValue;

    @ManyToOne()
    @JoinColumn(name = "id_cryptocurrency")
    private Cryptocurrency cryptocurrency;

    @ManyToOne()
    @JoinColumn(name = "id_user")
    private User user;
}