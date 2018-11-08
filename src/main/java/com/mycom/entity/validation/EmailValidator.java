package com.mycom.entity.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailValidator implements ConstraintValidator<Email, String> {

    @Override
    public void initialize(Email paramA) {
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext ctx) {
        if (email == null) {
            return false;
        }
        return email.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$");
    }
}