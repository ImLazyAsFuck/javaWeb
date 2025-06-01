package com.ss15.repository;

import com.ss15.model.CV;

import java.util.List;
import java.util.Optional;

public interface CVRepo {
    List<CV> findAll();
    Optional<CV> findById(Long id);
    CV save(CV cv);
    void deleteById(Long id);
}
