package com.ss10.controller;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.ss10.model.UploadFile;
import com.ss10.service.uploadservice.UploadService;
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
@RequestMapping("/upload")
public class UploadController{
    @Autowired
    private UploadService uploadService;
    @Autowired
    private Cloudinary cloudinary;

    @GetMapping
    public String upload(Model model){
        model.addAttribute("upload", new UploadFile());
        return "upload/uploadForm";
    }

    @PostMapping("/result")
    public String result(@ModelAttribute UploadFile uploadFile, Model model) throws IOException{
        Map<String, Object> result = cloudinary.uploader().upload(uploadFile.getFile().getBytes(), ObjectUtils.emptyMap());
        model.addAttribute("fileUrl", result.get("secure_url")).addAttribute("description", uploadFile.getDescription());
        return "upload/uploadResult";
    }
}
