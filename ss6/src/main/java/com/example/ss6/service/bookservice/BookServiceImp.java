package com.example.ss6.service.bookservice;

import com.example.ss6.dao.bookdao.BookDAO;
import com.example.ss6.dao.bookdao.BookDAOImp;
import com.example.ss6.model.Book;

import java.util.List;

public class BookServiceImp implements BookService{
    private final BookDAO BOOK_DAO = new BookDAOImp();

    @Override
    public List<Book> findAll(){
        return BOOK_DAO.findAll();
    }

    @Override
    public List<Book> findByTitle(String title){
        return BOOK_DAO.findByTitle(title);
    }


    @Override
    public Book findById(int id){
        return BOOK_DAO.findById(id);
    }

    @Override
    public boolean save(Book book){
        return BOOK_DAO.save(book);
    }

    @Override
    public boolean update(Book book){
        return BOOK_DAO.update(book);
    }

    @Override
    public boolean deleteById(int id){
        return BOOK_DAO.deleteById(id);
    }
}
