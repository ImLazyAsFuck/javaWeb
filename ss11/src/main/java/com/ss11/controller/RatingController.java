package com.ss11.controller;

import com.ss11.dto.Rating;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/b7")
public class RatingController {
    
    @GetMapping("/rate")
    public String showRatingForm(Model model) {
        if (!model.containsAttribute("rating")) {
            model.addAttribute("rating", new Rating());
        }
        return "b7/rateProduct";
    }
    
    @PostMapping("/rate")
    public String processRatingForm(@Valid @ModelAttribute("rating") Rating rating, 
                                   BindingResult bindingResult,
                                   Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("rating", rating);
            return "b7/rateProduct";
        }
        
        return "redirect:/b7/thank-you";
    }
    
    @GetMapping("/thank-you")
    public String showThankYouPage() {
        return "b7/thankYou";
    }
}
