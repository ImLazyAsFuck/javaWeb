package com.ss10.controller;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.ss10.model.Project;
import com.ss10.service.projectservice.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Controller
@RequestMapping("/project")
public class ProjectController{
    @Autowired
    private ProjectService projectService;
    @Autowired
    private Cloudinary cloudinary;

    @GetMapping
    public String projects(Model model){
        model.addAttribute("projects", projectService.findAll());
        return "project/projectList";
    }

    @GetMapping("/add")
    public String addProject(Model model){
        model.addAttribute("project", new Project());
        model.addAttribute("file", new MultipartFile[]{});
        return "project/addProject";
    }

    @GetMapping("/edit")
    public String editProject(Model model, @RequestParam int id){
        model.addAttribute("project", projectService.findById(id));
        model.addAttribute("file", new MultipartFile[]{});
        return "project/editProject";
    }

    @PostMapping("/add")
    public String saveProject(@ModelAttribute Project project, @RequestParam MultipartFile file, Model model) throws IOException{
        Map<String, Object> result = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
        project.setDocuments(result.get("secure_url").toString());
        if(projectService.save(project)){
            return "redirect:/project";
        }else{
            return "redirect:/project/add";
        }
    }

    @PostMapping("/edit")
    public String updateProject(@ModelAttribute Project project,
                                @RequestParam MultipartFile file,
                                Model model) throws IOException {
        Map<String, Object> result = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
        project.setDocuments(result.get("secure_url").toString());
        if (projectService.update(project)) {
            return "redirect:/project";
        } else {
            return "redirect:/project/edit?id=" + project.getId();
        }
    }


    @GetMapping("/delete")
    public String deleteProject(@RequestParam int id, Model model){
        projectService.delete(id);
        return "redirect:/project";
    }
}
