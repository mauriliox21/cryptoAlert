package com.maurlox21.cryptoalert.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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

    @Column(name = "tp_alert")
    private String tpAlert = TypeAlert.TO_UP.name();
    
    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "nr_sending")
    private Integer nrSending = 0;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Cryptocurrency.class)
    @JoinColumn(name = "id_cryptocurrency")
    private Cryptocurrency cryptocurrency;

    @ManyToOne()
    @JoinColumn(name = "id_user")
    private User user;

    public enum TypeAlert {
        TO_UP, TO_DOWN
    }

    public Alert (Long id, Double nrTargetValue, String tpAlert, Cryptocurrency cryptocurrency, User user){
        this.id = id;
        this.nrTargetValue = nrTargetValue;
        this.tpAlert = tpAlert;
        this.cryptocurrency = cryptocurrency;
        this.user = user;
    }

    public Alert (Long id, Double nrTargetValue, String tpAlert, Boolean isActive, Cryptocurrency cryptocurrency, User user){
        this.id = id;
        this.nrTargetValue = nrTargetValue;
        this.tpAlert = tpAlert;
        this.isActive = isActive;
        this.cryptocurrency = cryptocurrency;
        this.user = user;
    }
}