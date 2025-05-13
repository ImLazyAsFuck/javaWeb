package com.example.ss6.controller;

import com.example.ss6.model.Book;
import com.example.ss6.service.bookservice.BookService;
import com.example.ss6.service.bookservice.BookServiceImp;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/books")
public class BookController extends HttpServlet{
    private final BookService BOOK_SERVICE = new BookServiceImp();

    public void findBooks(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String searchType = req.getParameter("searchType");
        List<Book> books;
        
        if ("id".equals(searchType)) {
            String idKeyword = req.getParameter("idKeyword");
            if (idKeyword != null && !idKeyword.isEmpty()) {
                try {
                    int id = Integer.parseInt(idKeyword);
                    Book book = BOOK_SERVICE.findById(id);
                    books = new ArrayList<>();
                    if (book != null) {
                        books.add(book);
                    }
                } catch (NumberFormatException e) {
                    books = BOOK_SERVICE.findAll();
                }
            } else {
                books = BOOK_SERVICE.findAll();
            }
        } else {
            String titleKeyword = req.getParameter("titleKeyword");
            if (titleKeyword != null && !titleKeyword.isEmpty()) {
                books = BOOK_SERVICE.findByTitle(titleKeyword);
            } else {
                books = BOOK_SERVICE.findAll();
            }
        }
        
        req.setAttribute("books", books);
        req.getRequestDispatcher("/views/book/listBook.jsp").forward(req, res);
    }
    
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
        String action = req.getParameter("action");
        if(action == null || action.isEmpty()){
            toListBook(req, res);
            return;
        }
        switch(action){
            case "add" -> toAddBook(req, res);
            case "edit" -> toEditBook(req, res);
            case "delete" -> deleteBook(req, res);
            case "find" -> findBooks(req, res);
            default -> toListBook(req, res);
        }
    }

    public void toListBook(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
        req.setAttribute("books", BOOK_SERVICE.findAll());
        req.getRequestDispatcher("/views/book/listBook.jsp").forward(req, res);
    }

    public void toAddBook(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
        req.getRequestDispatcher("/views/book/formAdd.jsp").forward(req, res);
    }

    public void toEditBook(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
        int id = Integer.parseInt(req.getParameter("id"));
        Book book = BOOK_SERVICE.findById(id);
        req.setAttribute("book", book);
        req.getRequestDispatcher("/views/book/formEdit.jsp").forward(req, res);
    }

    public void deleteBook(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
        int id = Integer.parseInt(req.getParameter("id"));
        BOOK_SERVICE.deleteById(id);
        res.sendRedirect("/books");
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
        String action = req.getParameter("action");
        if(action == null || action.isEmpty()){
            toListBook(req, res);
            return;
        }
        switch(action){
            case "create" -> createBook(req, res);
            case "update" -> updateBook(req, res);
            default -> toListBook(req, res);
        }
    }

    public void createBook(HttpServletRequest req, HttpServletResponse res) throws IOException{
        Book book = new Book();
        book.setTitle(req.getParameter("title"));
        book.setAuthor(req.getParameter("author"));
        book.setCategory(req.getParameter("category"));
        book.setStock(Integer.parseInt(req.getParameter("stock")));
        if(BOOK_SERVICE.save(book)){
            res.sendRedirect("/books");
        }else{
            res.sendRedirect("/books?action=add");
        }
    }

    public void updateBook(HttpServletRequest req, HttpServletResponse res) throws IOException{
        int id = Integer.parseInt(req.getParameter("id"));
        Book book = BOOK_SERVICE.findById(id);
        book.setTitle(req.getParameter("title"));
        book.setAuthor(req.getParameter("author"));
        book.setCategory(req.getParameter("category"));
        book.setStock(Integer.parseInt(req.getParameter("stock")));
        if(BOOK_SERVICE.update(book)){
            res.sendRedirect("/books");
        }else{
            res.sendRedirect("/books?action=edit&id=" + id);
        }
    }
}
