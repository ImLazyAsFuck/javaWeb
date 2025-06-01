package com.b10.service;

import com.b10.model.Cv;
import com.b10.repo.CvRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CvServiceImpl implements CvService{
    @Autowired
    private CvRepo cvRepo;

    @Override
    public boolean save(Cv cv){
        return cvRepo.save(cv);
    }

    @Override
    public boolean update(Cv cv){
        return cvRepo.update(cv);
    }

    @Override
    public Cv getById(long id){
        return cvRepo.getById(id);
    }

    @Override
    public boolean delete(long id){
        return cvRepo.delete(id);
    }

    @Override
    public List<Cv> findAll(){
        return cvRepo.findAll();
    }

    @Override
    public boolean uniquePhone(String phone){
        return cvRepo.uniquePhone(phone);
    }

    @Override
    public boolean uniqueEmail(String email){
        return cvRepo.uniqueEmail(email);
    }

    @Override
    public List<Cv> searchByName(String name){
        return cvRepo.searchByName(name);
    }
}
