package com.ss11.validator.phone;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CustomPhoneValidator implements ConstraintValidator<CustomPhone, String>{
    @Override
    public void initialize(CustomPhone constraintAnnotation){
    }

    @Override
    public boolean isValid(String phone, ConstraintValidatorContext constraintValidatorContext){
        if (phone == null || phone.isEmpty()) {
            return false;
        }

        String regex = "^0\\d{9}$";

        return phone.matches(regex);
    }
}
