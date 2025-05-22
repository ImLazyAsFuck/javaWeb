package com.ss11.controller;

import com.ss11.dto.AdminGroup;
import com.ss11.dto.User;
import com.ss11.dto.UserGroup;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import javax.validation.Valid;
import java.util.Set;

@Controller
@RequestMapping("/b123456")
public class B123456Controller{
    @GetMapping
    public String showForm(@ModelAttribute User user, Model model){
        model.addAttribute("user", user);
        return "/b123456/register";
    }

    @PostMapping
    public String processForm(@ModelAttribute("user") User user, BindingResult bindingResult) {
        Class<?> group = "ADMIN".equalsIgnoreCase(user.getRole()) ? AdminGroup.class : UserGroup.class;

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<User>> violations = validator.validate(user, group);
        for (ConstraintViolation<User> violation : violations) {
            String propertyPath = violation.getPropertyPath().toString();
            String message = violation.getMessage();
            bindingResult.rejectValue(propertyPath, "Error", message);
        }

        if (bindingResult.hasErrors()) {
            return "/b123456/register";
        }

        return "redirect:/b123456/home";
    }


    @GetMapping("/result")
    public String showResult(){
        return "/b123456/home";
    }
}
