package com.ss11.controller;

import com.ss11.model.Category;
import com.ss11.service.CategoryService;
import com.ss11.validator.CategoryValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;
    
    @Autowired
    private CategoryValidator categoryValidator;
    
    @InitBinder("category")
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(categoryValidator);
    }

    @GetMapping
    public String listCategories(Model model) {
        List<Category> categories = categoryService.findAll();
        model.addAttribute("categories", categories);
        return "b89/list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("category", new Category());
        return "b89/add";
    }

    @PostMapping("/add")
    public String addCategory(@Valid @ModelAttribute("category") Category category,
                              BindingResult bindingResult,
                              RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "b89/add";
        }

        boolean success = categoryService.save(category);
        if (success) {
            redirectAttributes.addFlashAttribute("successMessage", "Thêm danh mục thành công");
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Có lỗi xảy ra khi thêm danh mục");
        }
        return "redirect:/categories";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model) {
        Category category = categoryService.findById(id);
        if (category == null) {
            model.addAttribute("errorMessage", "Không tìm thấy danh mục");
            return "redirect:/categories";
        }
        model.addAttribute("category", category);
        return "b89/edit";
    }

    @PostMapping("/edit/{id}")
    public String updateCategory(@PathVariable("id") Integer id,
                                @Valid @ModelAttribute("category") Category category,
                                BindingResult bindingResult,
                                RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "b89/edit";
        }

        category.setId(id);
        boolean success = categoryService.update(category);
        if (success) {
            redirectAttributes.addFlashAttribute("successMessage", "Cập nhật danh mục thành công");
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Có lỗi xảy ra khi cập nhật danh mục");
        }
        return "redirect:/categories";
    }

    @GetMapping("/delete/{id}")
    public String deleteCategory(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
        Category category = categoryService.findById(id);
        if (category == null) {
            redirectAttributes.addFlashAttribute("errorMessage", "Không tìm thấy danh mục");
            return "redirect:/categories";
        }
        
        boolean success = categoryService.delete(id);
        if (success) {
            redirectAttributes.addFlashAttribute("successMessage", "Xóa danh mục thành công");
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Có lỗi xảy ra khi xóa danh mục");
        }
        return "redirect:/categories";
    }
}
