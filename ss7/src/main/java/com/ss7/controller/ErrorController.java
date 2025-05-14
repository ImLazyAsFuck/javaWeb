package com.ss7.controller;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;


@ControllerAdvice
public class ErrorController{

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleNotFound(Exception exception, Model model) {
        model.addAttribute("message", "The page you are looking for does not exist.");
        return "error";
    }

}
