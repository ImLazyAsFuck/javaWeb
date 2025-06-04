package com.ss17.validator.uniqueproductname;

import com.ss17.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class IsProductNameExistValidator implements ConstraintValidator<IsProductNameExist, String>{
    @Autowired
    private ProductService productService;

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext){
        if(s == null || s.isEmpty()) return true;
        return !productService.isExist(s);
    }
}
