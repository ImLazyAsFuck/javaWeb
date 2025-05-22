package com.ss11.validator.uniqueCategory;

import com.ss11.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueCategoryNameValidator implements ConstraintValidator<UniqueCategoryName, String> {

    @Autowired
    private CategoryService categoryService;

    @Override
    public void initialize(UniqueCategoryName constraintAnnotation) {
    }

    @Override
    public boolean isValid(String categoryName, ConstraintValidatorContext context) {
        if (categoryName == null || categoryName.isEmpty()) {
            return true;
        }
        
        return categoryService.isCategoryNameUnique(categoryName);
    }
}
