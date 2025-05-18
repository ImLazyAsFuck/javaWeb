package com.ss10.service.documentservice;

import com.ss10.model.Document;

import java.util.List;

public interface DocumentService{
    List<Document> findAll();
    Document findById(int id);
    Document findByTitle(String title);
    boolean save(String title, String description, String file);
    boolean delete(int id);
}
