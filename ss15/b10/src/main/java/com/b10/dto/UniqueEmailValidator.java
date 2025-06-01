package com.b10.dto;

import com.b10.service.CvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String>{
    @Autowired
    private CvService cvService;

    @Override
    public void initialize(UniqueEmail constraintAnnotation){
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext){
        if(s == null || s.isEmpty()){
            return true;
        }
        return !cvService.uniqueEmail(s);
    }
}
