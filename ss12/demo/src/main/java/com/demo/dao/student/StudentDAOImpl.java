package com.demo.dao.student;

import com.cloudinary.Cloudinary;
import com.demo.model.Student;
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
public class StudentDAOImpl implements StudentDAO{
    @Autowired
    private DataSource dataSource;

    @Override
    public List<Student> getAllStudents(){
        Connection con = null;
        CallableStatement cs = null;
        List<Student> students = new ArrayList<>();
        try{
            con = dataSource.getConnection();
            cs = con.prepareCall("{call get_all_students()}");
            ResultSet rs = cs.executeQuery();
            while(rs.next()){
                Student student = new Student();
                student.setId(rs.getInt("id"));
                student.setName(rs.getString("name"));
                student.setEmail(rs.getString("email"));
                student.setDob(rs.getDate("dob").toLocalDate());
                students.add(student);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally{
            try{
                if(con != null) con.close();
                if(cs != null) cs.close();
            }catch(SQLException e){
                throw new RuntimeException(e);
            }
        }
        return students;
    }

    @Override
    public Student getStudentById(int id){
        Connection con = null;
        CallableStatement cs = null;
        Student student = null;
        try{
            con = dataSource.getConnection();
            cs = con.prepareCall("{call get_student_by_id(?)}");
            cs.setInt(1, id);
            ResultSet rs = cs.executeQuery();
            if(rs.next()){
                student = new Student();
                student.setId(rs.getInt("id"));
                student.setName(rs.getString("name"));
                student.setEmail(rs.getString("email"));
                student.setDob(rs.getDate("dob").toLocalDate());
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally{
            try{
                if(con != null) con.close();
                if(cs != null) cs.close();
            }catch(SQLException e){
                throw new RuntimeException(e);
            }
        }
        return student;
    }

    @Override
    public boolean saveStudent(Student student){
        Connection con = null;
        CallableStatement cs = null;
        try{
            con = dataSource.getConnection();
            cs = con.prepareCall("{call add_student(?, ?, ?)}");
            cs.setString(1, student.getName());
            cs.setString(2, student.getEmail());
            cs.setDate(3, java.sql.Date.valueOf(student.getDob()));
            int rowsAffected = cs.executeUpdate();
            return rowsAffected > 0;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }finally{
            try{
                if(cs != null) cs.close();
                if(con != null) con.close();
            }catch(SQLException e){
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public boolean updateStudent(Student student){
        Connection con = null;
        CallableStatement cs = null;
        try{
            con = dataSource.getConnection();
            cs = con.prepareCall("{call update_student(?, ?, ?, ?)}");
            cs.setInt(1, student.getId());
            cs.setString(2, student.getName());
            cs.setString(3, student.getEmail());
            cs.setDate(4, java.sql.Date.valueOf(student.getDob()));
            int rowsAffected = cs.executeUpdate();
            return rowsAffected > 0;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }finally{
            try{
                if(cs != null) cs.close();
                if(con != null) con.close();
            }catch(SQLException e){
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public boolean deleteStudent(int id){
        Connection con = null;
        CallableStatement cs = null;
        try{
            con = dataSource.getConnection();
            cs = con.prepareCall("{call delete_student(?)}");
            cs.setInt(1, id);
            int rowsAffected = cs.executeUpdate();
            return rowsAffected > 0;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }finally{
            try{
                if(cs != null) cs.close();
                if(con != null) con.close();
            }catch(SQLException e){
                throw new RuntimeException(e);
            }
        }
    }

}
