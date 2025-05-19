package com.ss10.service.projectservice;

import com.ss10.dao.projectdao.ProjectDAO;
import com.ss10.model.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService{
    @Override
    public List<Project> findAll(){
        return projectDAO.findAll();
    }

    @Override
    public Project findById(int id){
        return projectDAO.findById(id);
    }

    @Override
    public boolean save(Project project){
        return projectDAO.save(project);
    }

    @Override
    public boolean delete(int id){
        return projectDAO.delete(id);
    }

    @Override
    public boolean update(Project project){
        return projectDAO.update(project);
    }

    @Autowired
    private ProjectDAO projectDAO;

}
