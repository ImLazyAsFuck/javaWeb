package com.ss14.controller;

import com.ss14.service.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryServiceImpl categoryServiceImpl;

    @GetMapping
    public String getCategoryList(Model model) {
        model.addAttribute("categories", categoryServiceImpl.getAllCategories(LocaleContextHolder.getLocale()));
        return "b10/category_list";
    }

    @GetMapping("/add")
    public String getAddCategoryForm(Model model) {
        return "b10/category_form";
    }

    @PostMapping("/add")
    public String addCategory(@RequestParam String categoryNameVi,
                              @RequestParam String descriptionVi,
                              @RequestParam String categoryNameEn,
                              @RequestParam String descriptionEn,
                              Model model) {
        categoryServiceImpl.saveCategory(categoryNameVi, descriptionVi, categoryNameEn, descriptionEn);
        model.addAttribute("categories", categoryServiceImpl.getAllCategories(LocaleContextHolder.getLocale()));
        return "redirect:/category";
    }
}
