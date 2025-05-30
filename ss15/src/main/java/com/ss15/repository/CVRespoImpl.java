package com.ss15.repository;

import com.ss15.model.CV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class CVRespoImpl implements CVRepo{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final RowMapper<CV> cvRowMapper = (rs, rowNum) -> {
        CV cv = new CV();
        cv.setId(rs.getLong("id"));
        cv.setFullName(rs.getString("full_name"));
        cv.setEmail(rs.getString("email"));
        cv.setPhoneNumber(rs.getString("phone_number"));
        cv.setEducation(rs.getString("education"));
        cv.setExperience(rs.getString("experience"));
        cv.setSkills(rs.getString("skills"));
        return cv;
    };

    public List<CV> findAll() {
        return jdbcTemplate.execute("{call get_all_cvs()}", (CallableStatementCallback<List<CV>>)cs -> {
            boolean hasResult = cs.execute();
            List<CV> list = new ArrayList<>();
            if (hasResult) {
                try (ResultSet rs = cs.getResultSet()) {
                    while (rs.next()) {
                        list.add(cvRowMapper.mapRow(rs, rs.getRow()));
                    }
                }
            }
            return list;
        });
    }

    public Optional<CV> findById(Long id) {
        return jdbcTemplate.execute("{call get_cv_by_id(?)}", (CallableStatementCallback<Optional<CV>>) cs -> {
            cs.setLong(1, id);
            boolean hasResult = cs.execute();
            if (hasResult) {
                try (ResultSet rs = cs.getResultSet()) {
                    if (rs.next()) {
                        return Optional.of(cvRowMapper.mapRow(rs, rs.getRow()));
                    }
                }
            }
            return Optional.empty();
        });
    }

    public CV save(CV cv) {
        if (cv.getId() == null) {
            jdbcTemplate.execute("{call insert_cv(?, ?, ?, ?, ?, ?)}", (CallableStatementCallback<Void>) cs -> {
                cs.setString(1, cv.getFullName());
                cs.setString(2, cv.getEmail());
                cs.setString(3, cv.getPhoneNumber());
                cs.setString(4, cv.getEducation());
                cs.setString(5, cv.getExperience());
                cs.setString(6, cv.getSkills());
                cs.execute();
                return null;
            });
        } else {
            jdbcTemplate.execute("{call update_cv(?, ?, ?, ?, ?, ?, ?)}", (CallableStatementCallback<Void>) cs -> {
                cs.setLong(1, cv.getId());
                cs.setString(2, cv.getFullName());
                cs.setString(3, cv.getEmail());
                cs.setString(4, cv.getPhoneNumber());
                cs.setString(5, cv.getEducation());
                cs.setString(6, cv.getExperience());
                cs.setString(7, cv.getSkills());
                cs.execute();
                return null;
            });
        }
        return cv;
    }

    public void deleteById(Long id) {
        jdbcTemplate.execute("{call delete_cv(?)}", (CallableStatementCallback<Void>) cs -> {
            cs.setLong(1, id);
            cs.execute();
            return null;
        });
    }
}
