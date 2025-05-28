package com.ss14.controller;

import com.ss14.dto.UserDTO;
import com.ss14.model.User;
import com.ss14.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Controller
public class RegisterController {

    @Autowired
    private UserService userService;

    @Autowired
    private MessageSource messageSource;

    @GetMapping("/register")
    public String showForm(Model model) {
        model.addAttribute("user", new UserDTO());
        return "b6/register";
    }

    @PostMapping("/register")
    public String processForm(
            @ModelAttribute("user") @Valid UserDTO userDTO,
            BindingResult result,
            Model model,
            Locale locale
    ) {
        if (!userDTO.getPassword().equals(userDTO.getConfirmPassword())) {
            result.rejectValue("confirmPassword", "error.password.mismatch",
                    messageSource.getMessage("error.password.mismatch", null, locale));
        }

        if (result.hasErrors()) {
            return "b6/register";
        }
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setEmail(userDTO.getEmail());

        userService.save(user);

        model.addAttribute("success", messageSource.getMessage("register.success", null, locale));
        return "b6/register";
    }
}


