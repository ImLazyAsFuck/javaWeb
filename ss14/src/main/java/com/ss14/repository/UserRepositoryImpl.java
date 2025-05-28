package com.ss14.repository;

import com.ss14.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

@Repository
public class UserRepositoryImpl implements UserRepository{
    @Autowired
    private DataSource dataSource;

    @Override
    public void save(User user){
        Connection con = null;
        CallableStatement cs = null;
        try{
            con = dataSource.getConnection();
            cs = con.prepareCall("{CALL save_user(?, ?, ?)}");
            cs.setString(1, user.getUsername());
            cs.setString(2, user.getPassword());
            cs.setString(3, user.getEmail());
            cs.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                if(cs != null) cs.close();
                if(con != null) con.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
    }
}
