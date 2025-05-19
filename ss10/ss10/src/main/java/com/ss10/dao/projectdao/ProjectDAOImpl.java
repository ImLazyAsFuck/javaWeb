package com.ss10.dao.projectdao;

import com.ss10.model.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProjectDAOImpl implements ProjectDAO{
    @Autowired
    private DataSource dataSource;

    @Override
    public List<Project> findAll(){
        Connection con = null;
        CallableStatement cs = null;
        List<Project> projects = new ArrayList<>();
        try{
            con = dataSource.getConnection();
            cs = con.prepareCall("{call get_all_projects()}");
            ResultSet rs = cs.executeQuery();
            while(rs.next()){
                Project project = new Project();
                project.setId(rs.getInt("id"));
                project.setName(rs.getString("name"));
                project.setDescription(rs.getString("description"));
                project.setDocuments(rs.getString("file_url"));
                projects.add(project);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                dataSource.getConnection().close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return projects;
    }

    @Override
    public Project findById(int id){
        Connection con = null;
        CallableStatement cs = null;
        Project project = null;
        try{
            con = dataSource.getConnection();
            cs = con.prepareCall("{call get_project_by_id(?)}");
            cs.setInt(1, id);
            ResultSet rs = cs.executeQuery();
            while(rs.next()){
                project = new Project();
                project.setId(rs.getInt("id"));
                project.setName(rs.getString("name"));
                project.setDescription(rs.getString("description"));
                project.setDocuments(rs.getString("file_url"));
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                dataSource.getConnection().close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return project;
    }

    @Override
    public boolean save(Project project){
        Connection con = null;
        CallableStatement cs = null;
        try{
            con = dataSource.getConnection();
            cs = con.prepareCall("{call add_project(?, ?, ?)}");
            cs.setString(1, project.getName());
            cs.setString(2, project.getDescription());
            cs.setString(3, project.getDocuments());
            return cs.executeUpdate() > 0;
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                dataSource.getConnection().close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public boolean delete(int id){
        Connection con = null;
        CallableStatement cs = null;
        try{
            con = dataSource.getConnection();
            cs = con.prepareCall("{call delete_project_by_id(?)}");
            cs.setInt(1, id);
            return cs.executeUpdate() > 0;
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                dataSource.getConnection().close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public boolean update(Project project){
        Connection con = null;
        CallableStatement cs = null;
        try{
            con = dataSource.getConnection();
            cs = con.prepareCall("{call update_project(?, ?, ?, ?)}");
            cs.setInt(1, project.getId());
            cs.setString(2, project.getName());
            cs.setString(3, project.getDescription());
            cs.setString(4, project.getDocuments());
            return cs.executeUpdate() > 0;
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                dataSource.getConnection().close();
            }catch(
                    Exception e
            ){
                e.printStackTrace();
            }
        }
        return false;
    }
}
