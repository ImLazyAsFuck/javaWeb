package com.ss10.dao.documentdao;

import com.ss10.model.Document;

import java.util.List;

public interface DocumentDAO{
    List<Document> findAll();
    Document findById(int id);
    Document findByTitle(String title);
    boolean save(String title, String description, String file);
    boolean delete(int id);
}
