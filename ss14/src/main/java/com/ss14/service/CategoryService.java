package com.ss14.service;

import java.util.List;
import java.util.Locale;

public interface CategoryService{
    void saveCategory(String nameVi, String descVi, String nameEn, String descEn);
    List<?> getAllCategories(Locale locale);
}
