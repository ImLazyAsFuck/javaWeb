package com.ss10.service.uploadservice;

import com.ss10.dao.uploaddao.UploadDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UploadServiceImpl implements UploadService{
    @Autowired
    private UploadDAO uploadDAO;

    @Override
    public boolean save(String file, String description){
        return uploadDAO.save(file, description);
    }
}
