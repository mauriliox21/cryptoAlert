package com.maurlox21.cryptoalert.web.validation.impl;

import java.util.ArrayList;
import java.util.List;

import com.maurlox21.cryptoalert.web.validation.inte.ValidateString;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class StringValidator implements ConstraintValidator<ValidateString, String> {

    private List<String> valueList;

    @Override
    public void initialize(ValidateString constraintAnnotation){
        valueList = new ArrayList<>();
        for(String value: constraintAnnotation.acceptedValues()) {
            valueList.add(value);
        }
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return valueList.contains(value.toUpperCase());
    }

    
}
    
