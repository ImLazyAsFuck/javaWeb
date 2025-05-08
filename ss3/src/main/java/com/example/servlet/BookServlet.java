package com.example.servlet;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import com.example.model.Book;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "BookServlet", value = "/home")
public class BookServlet extends HttpServlet{
    public List<Book> books = new ArrayList<>();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        req.setAttribute("books", books);
        req.getRequestDispatcher("home.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String title = req.getParameter("title");
        String author = req.getParameter("author");
        String year = req.getParameter("year");
        Book book = new Book(title, author, Integer.parseInt(year));
        books.add(book);
        req.setAttribute("books", books);
        req.getRequestDispatcher("home.jsp").forward(req, resp);
    }

    public void destroy(){
    }
}