package com.ss10.controller;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.ss10.model.Document;
import com.ss10.service.documentservice.DocumentService;
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
@RequestMapping("/document")
public class DocumentController{
    @Autowired
    private DocumentService documentService;
    @Autowired
    private Cloudinary cloudinary;

    @GetMapping
    public String upload(Model model){
        model.addAttribute("document", new Document());
        return "document/documentForm";
    }

    @PostMapping("/upload")
    public String saveDocument(@ModelAttribute Document document, Model model) throws IOException{
        Map<String, Object> result = cloudinary.uploader().upload(document.getFile().getBytes(), ObjectUtils.emptyMap());
        document.setFileUrl(result.get("secure_url").toString());
        if(documentService.save(document.getTitle(), document.getDescription(), document.getFileUrl())){
            model.addAttribute("document", document);
            return "/document/docResult";
        }else{
            return "redirect:/document";
        }
    }
}
