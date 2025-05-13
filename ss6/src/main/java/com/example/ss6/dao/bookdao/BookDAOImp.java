package com.example.ss6.dao.bookdao;

import com.example.ss6.model.Book;
import com.example.ss6.utils.DBConnect;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDAOImp implements BookDAO{
    @Override
    public List<Book> findAll(){
        Connection con = null;
        CallableStatement cs = null;
        List<Book> books = new ArrayList<>();
        try{
            con = DBConnect.getConnection();
            cs = con.prepareCall("{call find_all_book()}");
            ResultSet rs = cs.executeQuery();
            while(rs.next()){
                Book book = new Book();
                book.setId(rs.getInt("id"));
                book.setTitle(rs.getString("title"));
                book.setAuthor(rs.getString("author"));
                book.setCategory(rs.getString("category"));
                book.setStock(rs.getInt("stock"));
                books.add(book);
            }
        }catch(Exception e){
            e.fillInStackTrace();
        }finally{
            DBConnect.closeConnection(con, cs);
        }
        return books;
    }

    @Override
    public List<Book> findByTitle(String title){
        Connection con = null;
        CallableStatement cs = null;
        List<Book> books = new ArrayList<>();
        try{
            con = DBConnect.getConnection();
            cs = con.prepareCall("{call find_book_by_title(?)}");
            cs.setString(1, title);
            ResultSet rs = cs.executeQuery();
            while(rs.next()){
                Book book = new Book();
                book.setId(rs.getInt("id"));
                book.setTitle(rs.getString("title"));
                book.setAuthor(rs.getString("author"));
                book.setCategory(rs.getString("category"));
                book.setStock(rs.getInt("stock"));
                books.add(book);
            }
        }catch(Exception e){
            e.fillInStackTrace();
        }finally{
            DBConnect.closeConnection(con, cs);
        }
        return books;
    }

    @Override
    public Book findById(int id){
        Connection con = null;
        CallableStatement cs = null;
        Book book = null;
        try{
            con = DBConnect.getConnection();
            cs = con.prepareCall("{call find_book_by_id(?)}");
            cs.setInt(1, id);
            ResultSet rs = cs.executeQuery();
            if(rs.next()){
                book = new Book();
                book.setId(rs.getInt("id"));
                book.setTitle(rs.getString("title"));
                book.setAuthor(rs.getString("author"));
                book.setCategory(rs.getString("category"));
                book.setStock(rs.getInt("stock"));
            }
        }catch(Exception e){
            e.fillInStackTrace();
        }finally{
            DBConnect.closeConnection(con, cs);
        }
        return book;
    }

    @Override
    public boolean save(Book book){
        Connection con = null;
        CallableStatement cs = null;
        try{
            con = DBConnect.getConnection();
            cs = con.prepareCall("{call add_book(?, ?, ?, ?)}");
            cs.setString(1, book.getTitle());
            cs.setString(2, book.getAuthor());
            cs.setString(3, book.getCategory());
            cs.setInt(4, book.getStock());
            return cs.executeUpdate() > 0;
        }catch(Exception e){
            e.fillInStackTrace();
        }finally{
            DBConnect.closeConnection(con, cs);
        }
        return false;
    }

    @Override
    public boolean update(Book book){
        Connection con = null;
        CallableStatement cs = null;
        try{
            con = DBConnect.getConnection();
            cs = con.prepareCall("{call update_book(?, ?, ?, ?, ?)}");
            cs.setInt(1, book.getId());
            cs.setString(2, book.getTitle());
            cs.setString(3, book.getAuthor());
            cs.setString(4, book.getCategory());
            cs.setInt(5, book.getStock());
            return cs.executeUpdate() > 0;
        }catch(Exception e){
            e.fillInStackTrace();
        }finally{
            DBConnect.closeConnection(con, cs);
        }
        return false;
    }

    @Override
    public boolean deleteById(int id){
        Connection con = null;
        CallableStatement cs = null;
        try{
            con = DBConnect.getConnection();
            cs = con.prepareCall("{call delete_book(?)}");
            cs.setInt(1, id);
            return cs.executeUpdate() > 0;
        }catch(Exception e){
            e.fillInStackTrace();
        }finally{
            DBConnect.closeConnection(con, cs);
        }
        return false;
    }
}
