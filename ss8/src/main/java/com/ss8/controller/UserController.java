package com.ss8.controller;

import com.ss8.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController{
    @GetMapping
    public ModelAndView user(){
        ModelAndView mv = new ModelAndView();
        List<User> users = Arrays.asList(
                new User(1, "Hello", "a@gmail.com", "09123456789", LocalDate.of(2000, 1, 1)),
                new User(2, "Sakura", "sakura@gmail.com", "09876543210", LocalDate.of(2001, 2, 14)),
                new User(3, "Shinji", "shinji@gmail.com", "0909123456", LocalDate.of(1999, 7, 30)),
                new User(4, "Tohru", "tohru@gmail.com", "0933123456", LocalDate.of(2002, 10, 5))
        );
        mv.addObject("users", users);
        mv.setViewName("user/user_list");
        return mv;
    }
}
