package com.ss10.dao.documentdao;

import com.ss10.model.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class DocumentDAOImpl implements DocumentDAO{
    @Autowired
    private DataSource dataSource;

    @Override
    public List<Document> findAll(){
        Connection con = null;
        CallableStatement cs = null;
        List<Document> documents = new ArrayList<>();
        try{
            con = dataSource.getConnection();
            cs = con.prepareCall("{call find_all_doc()}");
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                dataSource.getConnection().close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        return documents;
    }

    @Override
    public Document findById(int id){
        Connection con = null;
        CallableStatement cs = null;
        Document document = null;
        try{
            con = dataSource.getConnection();
            cs = con.prepareCall("{call find_doc_by_id(?)}");
            cs.setInt(1, id);
            ResultSet rs=  cs.executeQuery();
            while(rs.next()){
                document = new Document();
                document.setId(rs.getInt("id"));
                document.setTitle(rs.getString("title"));
                document.setDescription(rs.getString("description"));
                document.setFileUrl(rs.getString("file"));
            }
            return document;
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                dataSource.getConnection().close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public Document findByTitle(String title){
        return null;
    }

    @Override
    public boolean save(String title, String description, String file){
        Connection con = null;
        CallableStatement cs = null;
        try{
            con = dataSource.getConnection();
            cs = con.prepareCall("{call save(?, ?, ?)}");
            cs.setString(1, title);
            cs.setString(2, description);
            cs.setString(3, file);
            return cs.executeUpdate() > 0;
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                dataSource.getConnection().close();
            }catch(SQLException e){
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
            cs = con.prepareCall("{call deleteDocument(?)}");
            cs.setInt(1, id);
            return cs.executeUpdate() > 0;
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                dataSource.getConnection().close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        return false;
    }
}
