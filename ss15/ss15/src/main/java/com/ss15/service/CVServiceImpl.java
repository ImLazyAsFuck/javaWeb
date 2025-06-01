package com.ss15.service;

import com.ss15.model.CV;
import com.ss15.repository.CVRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CVServiceImpl implements CVService{
    @Autowired
    private CVRepo cvRepo;

    @Override
    public List<CV> findAll() {
        return cvRepo.findAll();
    }

    @Override
    public CV findById(Long id) {
        return cvRepo.findById(id).orElse(null);
    }

    @Override
    public CV save(CV resume) {
        return cvRepo.save(resume);
    }

    @Override
    public void deleteById(Long id) {
        cvRepo.deleteById(id);
    }
}
