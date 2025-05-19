package com.ss10.controller;

import com.ss10.model.Account;
import com.ss10.service.accountservice.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/account")
public class AccountController{
    @Autowired
    private AccountService accountService;

    private static Account currentAccount = null;

    @GetMapping
    public String login(Model model){
        model.addAttribute("account", new Account());
        return "account/registerForm";
    }

    @PostMapping
    public String register(@ModelAttribute Account account){
        if(accountService.register(account.getUsername(), account.getPassword(), account.getEmail())){
            currentAccount = account;
            return "redirect:/account/result";
        }else{
            return "redirect:/account";
        }
    }

    @GetMapping("/result")
    public String result(Model model){
        if(currentAccount != null){
            model.addAttribute("account", currentAccount);
            return "account/accountResult";
        }else{
            model.addAttribute("account", new Account());
            return "redirect:/account";
        }
    }
}
