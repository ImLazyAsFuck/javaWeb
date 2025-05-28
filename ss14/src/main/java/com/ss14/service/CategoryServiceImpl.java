package com.ss14.service;

import com.ss14.model.Category;
import com.ss14.repository.CategoryEnRepository;
import com.ss14.repository.CategoryViRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    private CategoryViRepository viRepo;
    @Autowired
    private CategoryEnRepository enRepo;

    @Override
    public void saveCategory(String nameVi, String descVi, String nameEn, String descEn) {
        Category vi = new Category(nameVi, descVi);
        Category en = new Category(nameEn, descEn);
        viRepo.save(vi);
        enRepo.save(en);
    }

    @Override
    public List<?> getAllCategories(Locale locale) {
        if (locale.getLanguage().equals("en")) {
            return enRepo.findAll();
        } else {
            return viRepo.findAll();
        }
    }
}
