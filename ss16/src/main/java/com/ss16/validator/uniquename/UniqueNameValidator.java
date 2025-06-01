package com.ss16.validator.uniquename;

import com.ss16.service.auth.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class UniqueNameValidator implements ConstraintValidator<UniqueName, String>{
    @Autowired
    private AuthService authService;

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext){
        return !authService.existsByUsername(s);
    }
}
