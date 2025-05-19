package com.ss10.dao.accountdao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

@Repository
public class AccountDAOImpl implements AccountDAO{
    @Autowired
    private DataSource dataSource;

    @Override
    public boolean register(String username, String password, String email){
        Connection con = null;
        CallableStatement cs = null;
        try{
            con = dataSource.getConnection();
            cs = con.prepareCall("{call register(?, ?, ?)}");
            cs.setString(1, username);
            cs.setString(2, password);
            cs.setString(3, email);
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
    public boolean login(String username, String password){
        Connection con = null;
        CallableStatement cs = null;
        try{
            con = dataSource.getConnection();
            cs = con.prepareCall("{call login(?, ?)}");
            cs.setString(1, username);
            cs.setString(2, password);
            return cs.executeQuery().next();
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
