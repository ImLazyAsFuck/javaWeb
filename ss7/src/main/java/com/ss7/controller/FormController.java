package com.ss7.controller;

import com.ss7.model.Feedback;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Controller
@RequestMapping("/form")
public class FormController {
    
    // In-memory storage for feedback items
    private static final List<Feedback> feedbackList = new ArrayList<>();
    
    // Pattern for phone number validation (10 digits)
    private static final Pattern PHONE_PATTERN = Pattern.compile("^\\d{10}$");

    @GetMapping("")
    public String showForm(Model model) {
        model.addAttribute("feedback", new Feedback());
        return "form";
    }

    @PostMapping("/submit")
    public String submitForm(@ModelAttribute("feedback") Feedback feedback, 
                             Model model, 
                             RedirectAttributes redirectAttributes) {
        
        // Validate required fields
        if (feedback.getName() == null || feedback.getName().trim().isEmpty()) {
            model.addAttribute("errorMessage", "Họ và tên là trường bắt buộc");
            return "form";
        }
        
        if (feedback.getContent() == null || feedback.getContent().trim().isEmpty()) {
            model.addAttribute("errorMessage", "Nội dung góp ý là trường bắt buộc");
            return "form";
        }
        
        // Validate phone number format if provided
        if (feedback.getPhone() != null && !feedback.getPhone().isEmpty() && 
            !PHONE_PATTERN.matcher(feedback.getPhone()).matches()) {
            model.addAttribute("errorMessage", "Số điện thoại không hợp lệ (phải có 10 chữ số)");
            return "form";
        }
        
        // Add feedback to the list
        feedbackList.add(feedback);
        
        // Add success message
        redirectAttributes.addFlashAttribute("successMessage", "Góp ý của bạn đã được ghi nhận!");
        
        // Display result view first, then redirect to list
        model.addAttribute("feedback", feedback);
        return "result";
    }
    
    @GetMapping("/list")
    public String listFeedback(Model model) {
        model.addAttribute("feedbackList", feedbackList);
        return "list";
    }
}
