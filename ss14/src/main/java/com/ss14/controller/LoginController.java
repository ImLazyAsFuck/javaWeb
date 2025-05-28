package com.ss14.controller;

import com.ss14.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController{

    @Autowired
    private AuthService authService;

    @GetMapping("/login")
    public String showLoginForm(Model model,
                                @CookieValue(value = "rememberMeUsername", defaultValue = "") String cookieUsername) {
        if (!cookieUsername.isEmpty()) {
            model.addAttribute("cookieUsername", cookieUsername);
        }
        return "b1_7/login";
    }

    @PostMapping("/login")
    public String processLogin(@RequestParam String username,
                               @RequestParam String password,
                               @RequestParam(required = false) boolean rememberMe,
                               HttpSession session,
                               HttpServletResponse response,
                               Model model) {
        if (authService.authenticate(username, password)) {

            session.setAttribute("username", username);
            session.setMaxInactiveInterval(30 * 60);

            if (rememberMe) {
                Cookie cookie = new Cookie("rememberMeUsername", username);
                cookie.setMaxAge(7 * 24 * 60 * 60);
                cookie.setPath("/");
                response.addCookie(cookie);
            }
            return "redirect:/welcome";
        } else {
            model.addAttribute("error", "Invalid username or password");
            return "b1_7/login";
        }
    }

    @GetMapping("/welcome")
    public String showWelcomePage(HttpSession session) {
        if (session.getAttribute("username") == null) {
            return "redirect:/login";
        }
        return "b1_7/welcome";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session, HttpServletResponse response) {
        session.invalidate();
        Cookie cookie = new Cookie("rememberMeUsername", "");
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
        return "redirect:/login";
    }
}
