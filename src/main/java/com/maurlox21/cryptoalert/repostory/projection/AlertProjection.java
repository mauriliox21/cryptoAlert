package com.maurlox21.cryptoalert.repostory.projection;

public interface AlertProjection {

    Long getId();

    Double getNrTargetValue();

    String getTpAlert();

    Boolean getIsActive();

    CryptocurrencyProjection getCryptocurrency();
    
}