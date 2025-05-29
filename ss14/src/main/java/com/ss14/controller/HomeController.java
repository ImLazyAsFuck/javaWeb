package com.ss14.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

@Controller
@RequestMapping("/b3")
public class HomeController {

    @GetMapping
    public String home(Model model, HttpServletRequest request) {
        return "b3/home";
    }


    @PostMapping("/change-language")
    public String changeLanguage(HttpServletRequest request, HttpServletResponse response,
                                 @RequestParam("lang") String lang) {
        LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
        if (localeResolver != null) {
            Locale locale = new Locale(lang);
            localeResolver.setLocale(request, response, locale);
        }
        return "redirect:/b3";
    }
}

