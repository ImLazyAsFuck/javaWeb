package com.b10.controller;

import com.cloudinary.Cloudinary;
import com.b10.dto.CvDto;
import com.b10.model.Cv;
import com.b10.service.CvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;

@Controller
@RequestMapping("/cv")
public class CvController{
    @Autowired
    private Cloudinary cloudinary;
    @Autowired
    private CvService cvService;

    @GetMapping
    public String list(Model model, @RequestParam(required = false) String name){
        if(name != null && !name.isEmpty()){
            model.addAttribute("cvs", cvService.searchByName(name));
            return "cv_list";
        }
        model.addAttribute("cvs", cvService.findAll());
        return "cv_list";
    }

    @GetMapping("/add")
    public String add(@ModelAttribute("cvDto") CvDto cvDto, Model model){
        model.addAttribute("cvDto", cvDto);
        return "add_cv";
    }

    @GetMapping("/edit")
    public String edit(@RequestParam int id, Model model){
        model.addAttribute("cv", cvService.getById(id));
        return "edit_cv";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam int id, Model model){
        cvService.delete(id);
        return "redirect:/cv";
    }

    @PostMapping("/add")
    public String save(@Valid @ModelAttribute("cvDto") CvDto cvDto, BindingResult bindingResult, Model model) throws IOException{
        if(bindingResult.hasErrors()){
            return "add_cv";
        }
        if(cvDto.getFile() != null && !cvDto.getFile().isEmpty()){
            String url = cloudinary.uploader().upload(cvDto.getFile().getBytes(), null).get("url").toString();
            cvDto.setImage(url);
        }else{
            cvDto.setImage("");
        }
        Cv cv = new Cv();
        cv.setFullname(cvDto.getFullname());
        cv.setEmail(cvDto.getEmail());
        cv.setPhone(cvDto.getPhone());
        cv.setEducation(cvDto.getEducation());
        cv.setExperience(cvDto.getExperience());
        cv.setSkills(cvDto.getSkills());
        cv.setImage(cvDto.getImage());
        cvService.save(cv);
        return "redirect:/cv";
    }

    @PostMapping("edit")
    public String update(@RequestParam int id, @Valid @ModelAttribute("cv") CvDto cvDto, BindingResult bindingResult, Model model) throws IOException{
        if(bindingResult.hasErrors()){
            return "edit_cv";
        }
        if (cvDto.getFile() != null && !cvDto.getFile().isEmpty()) {
            String url = cloudinary.uploader().upload(cvDto.getFile().getBytes(), null).get("url").toString();
            cvDto.setImage(url);
        } else {
            Cv existingCv = cvService.getById(id);
            cvDto.setImage(existingCv.getImage());
        }
        Cv cv = new Cv();
        cv.setId(id);
        cv.setFullname(cvDto.getFullname());
        cv.setEmail(cvDto.getEmail());
        cv.setPhone(cvDto.getPhone());
        cv.setEducation(cvDto.getEducation());
        cv.setExperience(cvDto.getExperience());
        cv.setSkills(cvDto.getSkills());
        cv.setImage(cvDto.getImage());
        cvService.update(cv);
        return "redirect:/cv";
    }
}
