package com.ss10.service.documentservice;

import com.ss10.dao.documentdao.DocumentDAO;
import com.ss10.model.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentServiceImpl implements DocumentService{
    @Autowired
    private DocumentDAO documentDAO;

    @Override
    public List<Document> findAll(){
        return documentDAO.findAll();
    }

    @Override
    public Document findById(int id){
        return documentDAO.findById(id);
    }

    @Override
    public Document findByTitle(String title){
        return null;
    }

    @Override
    public boolean save(String title, String description, String file){
        return documentDAO.save(title, description, file);
    }

    @Override
    public boolean delete(int id){
        return documentDAO.delete(id);
    }
}
