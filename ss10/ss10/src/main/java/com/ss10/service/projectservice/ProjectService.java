package com.ss10.service.projectservice;

import com.ss10.model.Project;

import java.util.List;

public interface ProjectService{
    List<Project> findAll();
    Project findById(int id);
    boolean save(Project project);
    boolean delete(int id);
    boolean update(Project project);
}
