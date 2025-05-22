package com.ss11.validator;

import com.ss11.model.Category;
import com.ss11.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class CategoryValidator implements Validator {

    @Autowired
    private CategoryService categoryService;

    @Override
    public boolean supports(Class<?> clazz) {
        return Category.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Category category = (Category) target;

        if (category.getCategoryName() == null || category.getCategoryName().trim().isEmpty()) {
            return;
        }

        if (category.getId() == null) {
            if (!categoryService.isCategoryNameUnique(category.getCategoryName())) {
                errors.rejectValue("categoryName", "error.category", "Tên danh mục đã tồn tại");
            }
        } else {
            if (!categoryService.isCategoryNameUnique(category.getCategoryName(), category.getId())) {
                errors.rejectValue("categoryName", "error.category", "Tên danh mục đã tồn tại");
            }
        }
    }
}
