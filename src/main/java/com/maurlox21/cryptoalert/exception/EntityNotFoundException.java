package com.maurlox21.cryptoalert.exception;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException( String message){
        super(message);
    }
}
