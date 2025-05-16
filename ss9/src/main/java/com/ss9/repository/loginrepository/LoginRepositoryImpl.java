package com.ss9.repository.loginrepository;

import com.ss9.model.Customer;
import com.ss9.model.Gender;
import com.ss9.utils.DBConnect;
import org.springframework.stereotype.Repository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;

@Repository
public class LoginRepositoryImpl implements LoginRepository{
    @Override
    public Customer login(Customer customer){
        Connection con = null;
        CallableStatement cs = null;
        Customer result = null;
        try{
            con = DBConnect.getConnection();
            cs = con.prepareCall("{call get_customer_by_username(?)}");
            cs.setString(1, customer.getUsername());
            ResultSet rs = cs.executeQuery();
            while(rs.next()){
                result = new Customer();
                result.setId(rs.getLong("c_id"));
                result.setUsername(rs.getString("c_username"));
                result.setPassword(rs.getString("c_password"));
                result.setPhone(rs.getString("c_phone"));
                result.setAddress(rs.getString("c_address"));
                result.setGender(Gender.valueOf(rs.getString("c_gender")));
                result.setEmail(rs.getString("c_email"));
            }
        }catch(Exception e){
            e.fillInStackTrace();
        }finally{
            DBConnect.closeConnection(con, cs);
        }
        return result;
    }
}
