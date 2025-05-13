package com.example.ss6.dao.bookdao;

import com.example.ss6.model.Book;

import java.util.List;

public interface BookDAO{
    List<Book> findAll();
    List<Book> findByTitle(String title);
    Book findById(int id);
    boolean save(Book book);
    boolean update(Book book);
    boolean deleteById(int id);
}
