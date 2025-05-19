package com.ss10.dao.uploaddao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.CallableStatement;
import java.sql.Connection;

@Repository
public class UploadDAOImpl implements UploadDAO{
    @Autowired
    private DataSource dataSource;

    @Override
    public boolean save(String file, String description){
        Connection con = null;
        CallableStatement cs = null;
        try{
            con = dataSource.getConnection();
            cs = con.prepareCall("{call save_upload(?, ?)}");
            cs.setString(1, file);
            cs.setString(2, description);
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
}
