package com.ss14.controller;

import com.ss14.model.Transaction;
import com.ss14.model.UserSessionData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

@Controller
@RequestMapping("/transaction")
@SessionAttributes("userData")
public class TransactionController {
    @Autowired
    private LocaleResolver localeResolver;

    @ModelAttribute("userData")
    public UserSessionData initUserData(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        String username = "Guest";
        if (cookies != null) {
            for (Cookie c : cookies) {
                if ("username".equals(c.getName())) {
                    username = c.getValue();
                }
            }
        }
        UserSessionData data = new UserSessionData();
        data.setUsername(username);
        return data;
    }

    @GetMapping
    public String home(@ModelAttribute("userData") UserSessionData data, Model model) {
        model.addAttribute("transactions", data.getTransactions());
        return "b9/home";
    }

    @GetMapping("/form")
    public String form(Model model) {
        model.addAttribute("transaction", new Transaction());
        return "b9/form";
    }

    @PostMapping("/addTransaction")
    public String add(@ModelAttribute("transaction") Transaction t,
                      @ModelAttribute("userData") UserSessionData data) {
        data.getTransactions().add(t);
        return "redirect:/transaction";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam int index,
                         @ModelAttribute("userData") UserSessionData data) {
        if (index >= 0 && index < data.getTransactions().size()) {
            data.getTransactions().remove(index);
        }
        return "redirect:/transaction";
    }

    @GetMapping("/setLang")
    public String setLang(@RequestParam String lang, HttpServletRequest request,
                          HttpServletResponse response) {
        Locale locale = new Locale(lang);
        localeResolver.setLocale(request, response, locale);
        return "redirect:/transaction";
    }
}
