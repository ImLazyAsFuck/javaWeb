package com.ss10.controller;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.ss10.model.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.Map;

@Controller
@RequestMapping("/profile")
public class ProfileController{
    @Autowired
    private Cloudinary cloudinary;

    @GetMapping
    public String profile(Model model){
        model.addAttribute("profile", new Profile());
        return "profile/uploadForm";
    }

    @PostMapping
    public String uploadProfile(@ModelAttribute Profile profile, Model model) throws IOException{
        Map<String, Object> result = cloudinary.uploader().upload(profile.getAvatar().getBytes(), ObjectUtils.emptyMap());
        model.addAttribute("imgUrl", result.get("secure_url"));
        model.addAttribute("username", profile.getUsername());
        return "/profile/profile";
    }
}
