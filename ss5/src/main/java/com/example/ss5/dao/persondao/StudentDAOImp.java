package com.example.ss5.dao.persondao;

import com.example.ss5.model.Student;
import com.example.ss5.utils.DBConnect;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;

public class StudentDAOImp implements StudentDAO{
    @Override
    public List<Student> findAll() {
        Connection con = null;
        CallableStatement cs = null;
        List<Student> students = new java.util.ArrayList<>();
        try {
            con = DBConnect.getConnection();
            cs = con.prepareCall("{call get_all_student()}");
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                Student student = new Student();
                student.setId(rs.getInt("id"));
                student.setName(rs.getString("name"));
                student.setAge(rs.getInt("age"));
                student.setAddress(rs.getString("address"));
                students.add(student);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeConnection(con, cs);
        }
        return students;
    }
    
    @Override
    public boolean save(Student student) {
        Connection con = null;
        CallableStatement cs = null;
        try {
            con = DBConnect.getConnection();
            cs = con.prepareCall("{call save_student(?,?,?)}");
            cs.setString(1, student.getName());
            cs.setInt(2, student.getAge());
            cs.setString(3, student.getAddress());
            cs.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            DBConnect.closeConnection(con, cs);
        }
    }
    
    @Override
    public List<Student> findPage(int page) {
        Connection con = null;
        CallableStatement cs = null;
        List<Student> students = new java.util.ArrayList<>();
        try {
            con = DBConnect.getConnection();
            cs = con.prepareCall("{call get_student_by_page(?)}");
            cs.setInt(1, page);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                Student student = new Student();
                student.setId(rs.getInt("id"));
                student.setName(rs.getString("name"));
                student.setAge(rs.getInt("age"));
                student.setAddress(rs.getString("address"));
                students.add(student);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeConnection(con, cs);
        }
        return students;
    }
    
    @Override
    public boolean delete(int id) {
        Connection con = null;
        CallableStatement cs = null;
        try {
            con = DBConnect.getConnection();
            cs = con.prepareCall("{call delete_student(?)}");
            cs.setInt(1, id);
            return cs.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            DBConnect.closeConnection(con, cs);
        }
    }
    
    @Override
    public boolean update(Student student) {
        Connection con = null;
        CallableStatement cs = null;
        try {
            con = DBConnect.getConnection();
            cs = con.prepareCall("{call update_student(?,?,?,?)}");
            cs.setInt(1, student.getId());
            cs.setString(2, student.getName());
            cs.setInt(3, student.getAge());
            cs.setString(4, student.getAddress());
            return cs.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            DBConnect.closeConnection(con, cs);
        }
    }
    
    @Override
    public int count() {
        Connection con = null;
        CallableStatement cs = null;
        try {
            con = DBConnect.getConnection();
            java.sql.Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) as total FROM student");
            if (rs.next()) {
                int count = rs.getInt("total");
                System.out.println("Total students found: " + count);
                return count;
            }
            return 0;
        } catch (Exception e) {
            System.out.println("Error counting students: " + e.getMessage());
            e.printStackTrace();
            return 0;
        } finally {
            DBConnect.closeConnection(con, cs);
        }
    }
}
