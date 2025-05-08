package com.example.servlet;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "main", value = "/")
public class Main extends HttpServlet{
    private String message;

    public void init(){
        message = "Hello World!";
    }



    public void destroy(){
    }
}