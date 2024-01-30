package com.maurlox21.cryptoalert.repostory.projection;

public interface AlertProjection {

    Long getId();

    Double getNrTargetValue();

    CryptocurrencyProjection getCryptocurrency();
    
}