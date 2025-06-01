package com.ss16.dto.bus;

import com.ss16.service.bus.BusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.annotation.Annotation;

@Component
public class UniqueLicenseplateValidator implements ConstraintValidator<UniqueLicenseplate, String>{
    @Autowired
    private BusService busService;

    @Override
    public void initialize(UniqueLicenseplate constraintAnnotation){
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext){
        if(s == null || s.isEmpty()) return true;
        if(s.length() > 8) return true;
        return !busService.exists(s);
    }
}
