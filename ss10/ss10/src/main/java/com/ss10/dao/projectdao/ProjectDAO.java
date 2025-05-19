package com.ss10.dao.projectdao;

import com.ss10.model.Project;

import java.util.List;

public interface ProjectDAO{
    List<Project> findAll();
    Project findById(int id);
    boolean save(Project project);
    boolean delete(int id);
    boolean update(Project project);
}
