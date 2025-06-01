    package com.ss15.controller;

    import com.ss15.dto.UserDTO;
    import com.ss15.model.User;
    import org.springframework.stereotype.Controller;
    import org.springframework.ui.Model;
    import org.springframework.validation.BindingResult;
    import org.springframework.web.bind.annotation.GetMapping;
    import org.springframework.web.bind.annotation.ModelAttribute;
    import org.springframework.web.bind.annotation.PostMapping;
    import org.springframework.web.bind.annotation.RequestMapping;

    import javax.validation.Valid;

    @Controller
    @RequestMapping("/user")
    public class UserController{
        private final User USER = new User();

        @GetMapping("register")
        public String form(Model model){
            model.addAttribute("user", new UserDTO());
            return "b3/form";
        }

        @PostMapping("register")
        public String submit(@Valid @ModelAttribute("user") UserDTO userDTO, BindingResult bindingResult, Model model){
            if(bindingResult.hasErrors()){
                return "b3/form";
            }
            USER.setUsername(userDTO.getUsername());
            USER.setPassword(userDTO.getPassword());
            USER.setEmail(userDTO.getEmail());
            return "redirect:result";
        }

        @GetMapping("/result")
        public String result(Model model){
            model.addAttribute("user", USER);
            return "b3/result";
        }
    }
