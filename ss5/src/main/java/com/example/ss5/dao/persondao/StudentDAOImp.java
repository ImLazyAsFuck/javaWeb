package com.example.ss5.dao.persondao;

import com.example.ss5.model.Student;
import com.example.ss5.utils.DBConnect;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.PreparedStatement;
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
    
    @Override
    public Student findById(int id) {
        Connection con = null;
        PreparedStatement ps = null;
        Student student = null;
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement("SELECT id, name, age, address FROM student WHERE id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                student = new Student();
                student.setId(rs.getInt("id"));
                student.setName(rs.getString("name"));
                student.setAge(rs.getInt("age"));
                student.setAddress(rs.getString("address"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return student;
    }
    

}
