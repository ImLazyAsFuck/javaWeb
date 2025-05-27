package com.hackathon.dto;

import com.hackathon.repository.product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class UniqueProductNameValidator implements ConstraintValidator<UniqueProductName, String>{
    @Autowired
    private ProductRepository productRepository;

    @Override
    public boolean isValid(String name, ConstraintValidatorContext context) {
        if (name == null || name.trim().isEmpty()) return true;
        return !productRepository.existsByName(name);
    }
}
