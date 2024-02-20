package com.maurlox21.cryptoalert.web.validation.inte;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.maurlox21.cryptoalert.web.validation.impl.StringValidator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.ReportAsSingleViolation;

@Documented
@Constraint(validatedBy = StringValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@ReportAsSingleViolation
public @interface ValidateString {
    
    String[] acceptedValues();

    String message() default "";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default {};
}
