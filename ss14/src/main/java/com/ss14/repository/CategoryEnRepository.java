package com.ss14.repository;

import com.ss14.model.Category;

import java.util.List;
import java.util.Locale;

public interface CategoryEnRepository{
    void save(Category category);
    List<?> findAll();
}
