package com.example.ss5.dao.taskdao;

import com.example.ss5.model.Task;
import com.example.ss5.utils.DBConnect;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TaskDAOImp implements TaskDAO{

    @Override
    public List<Task> findAll(){
        Connection con = null;
        CallableStatement cs = null;
        List<Task> tasks = new ArrayList<>();
        try{
            con = DBConnect.getConnection();
            cs = con.prepareCall("{call get_all_task()}");
            ResultSet rs = cs.executeQuery();
            while(rs.next()){
                Task task = new Task();
                task.setId(rs.getInt("id"));
                task.setName(rs.getString("name"));
                task.setCompleted(rs.getBoolean("completed"));
                tasks.add(task);
            }
        }catch(Exception e){
            e.fillInStackTrace();
        }finally{
            DBConnect.closeConnection(con, cs);
        }
        return tasks;
    }

    @Override
    public boolean save(Task task){
        Connection con = null;
        CallableStatement cs = null;
        try{
            con = DBConnect.getConnection();
            cs = con.prepareCall("{call save_task(?)}");
            cs.setString(1, task.getName());
            return cs.executeUpdate() > 0;
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            DBConnect.closeConnection(con, cs);
        }
        return false;
    }

    @Override
    public boolean delete(int id){
        Connection con = null;
        CallableStatement cs = null;
        try{
            con = DBConnect.getConnection();
            cs = con.prepareCall("{call delete_task(?)}");
            cs.setInt(1, id);
            return cs.executeUpdate() > 0;
        }catch(Exception e){
            e.fillInStackTrace();
        }finally{
            DBConnect.closeConnection(con, cs);
        }
        return false;
    }

    @Override
    public boolean update(Task task){
        Connection con = null;
        CallableStatement cs = null;
        try{
            con = DBConnect.getConnection();
            cs = con.prepareCall("{call update_task(?,?,?)}");
            cs.setInt(1, task.getId());
            cs.setString(2, task.getName());
            cs.setBoolean(3, task.isCompleted());
            return cs.executeUpdate() > 0;
        }catch(Exception e){
            e.fillInStackTrace();
        }finally{
            DBConnect.closeConnection(con, cs);
        }
        return false;
    }
}
