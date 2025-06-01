package com.b10.repo;

import com.b10.model.Cv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CvRepoImpl implements CvRepo{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public boolean save(Cv cv){
        String sql = "call add_cv(?,?,?,?,?,?,?)";
        return jdbcTemplate.update(sql, cv.getFullname(), cv.getEmail(), cv.getPhone(), cv.getEducation(), cv.getExperience(), cv.getSkills(), cv.getImage()) > 0;
    }

    @Override
    public boolean update(Cv cv){
        String sql = "call update_cv(?,?,?,?,?,?,?,?)";
        return jdbcTemplate.update(sql, cv.getId(), cv.getFullname(), cv.getEmail(), cv.getPhone(), cv.getEducation(), cv.getExperience(), cv.getSkills(), cv.getImage()) > 0;
    }

    @Override
    public Cv getById(long id){
        String sql = "call get_cv_by_id(?)";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, (rs, rowNum) -> {
            Cv cv = new Cv();
            cv.setId(rs.getLong("id"));
            cv.setFullname(rs.getString("fullname"));
            cv.setEmail(rs.getString("email"));
            cv.setPhone(rs.getString("phone"));
            cv.setEducation(rs.getString("education"));
            cv.setExperience(rs.getString("experience"));
            cv.setSkills(rs.getString("skills"));
            cv.setImage(rs.getString("image"));
            return cv;
        });
    }

    @Override
    public boolean delete(long id){
        String sql = "call delete_cv(?)";
        return jdbcTemplate.update(sql, id) > 0;
    }

    @Override
    public List<Cv> findAll(){
        String sql = "call get_all_cvs()";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Cv cv = new Cv();
            cv.setId(rs.getLong("id"));
            cv.setFullname(rs.getString("fullname"));
            cv.setEmail(rs.getString("email"));
            cv.setPhone(rs.getString("phone"));
            cv.setEducation(rs.getString("education"));
            cv.setExperience(rs.getString("experience"));
            cv.setSkills(rs.getString("skills"));
            cv.setImage(rs.getString("image"));
            return cv;
        });
    }

    @Override
    public boolean uniquePhone(String phone){
        String sql = "call unique_phone(?)";
        return jdbcTemplate.queryForObject(sql, new Object[]{phone}, Boolean.class);
    }

    @Override
    public boolean uniqueEmail(String email){
        String sql = "call unique_email(?)";
        return jdbcTemplate.queryForObject(sql, new Object[]{email}, Boolean.class);
    }

    @Override
    public List<Cv> searchByName(String name){
        String sql = "call search_by_name(?)";
        return jdbcTemplate.query(sql, new Object[]{name}, (rs, rowNum) -> {
            Cv cv = new Cv();
            cv.setId(rs.getLong("id"));
            cv.setFullname(rs.getString("fullname"));
            cv.setEmail(rs.getString("email"));
            cv.setPhone(rs.getString("phone"));
            cv.setEducation(rs.getString("education"));
            cv.setExperience(rs.getString("experience"));
            cv.setSkills(rs.getString("skills"));
            cv.setImage(rs.getString("image"));
            return cv;
        });
    }
}
