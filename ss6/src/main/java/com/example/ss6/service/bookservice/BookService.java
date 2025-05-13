package com.example.ss6.service.bookservice;

import com.example.ss6.model.Book;

import java.util.List;

public interface BookService{
    List<Book> findAll();
    List<Book> findByTitle(String title);
    Book findById(int id);
    boolean save(Book book);
    boolean update(Book book);
    boolean deleteById(int id);
}
