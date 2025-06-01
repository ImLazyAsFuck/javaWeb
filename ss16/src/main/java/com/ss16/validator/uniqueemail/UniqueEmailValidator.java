package com.ss16.validator.uniqueemail;

import com.ss16.service.auth.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String>{
    @Autowired
    private AuthService authService;

    @Override
    public void initialize(UniqueEmail constraintAnnotation){

    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext){
        return !authService.existsByEmail(s);
    }
}
