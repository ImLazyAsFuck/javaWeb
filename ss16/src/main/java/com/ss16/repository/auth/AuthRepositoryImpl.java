package com.ss16.repository.auth;

import com.ss16.model.user.User;
import com.ss16.model.user.UserRole;
import com.ss16.model.user.UserStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class AuthRepositoryImpl implements AuthRepository{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public boolean existsByUsername(String username){
        String sql = "CALL unique_username(?)";
        return jdbcTemplate.queryForObject(sql, Boolean.class, username);
    }

    @Override
    public boolean existsByEmail(String email){
        String sql = "CALL unique_email(?)";
        return jdbcTemplate.queryForObject(sql, Boolean.class, email);
    }

    @Override
    public void save(User user){
        String sql = "CALL register(?, ?, ?)";
        jdbcTemplate.update(sql, user.getUsername(), user.getPassword(), user.getEmail());

    }


    public User get(String username, String password) {
        String sql = "CALL login(?, ?)";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{username, password}, (rs, rowNum) ->
                    new User(
                            rs.getLong("id"),
                            rs.getString("username"),
                            rs.getString("password"),
                            rs.getString("email"),
                            UserRole.valueOf(rs.getString("role").toUpperCase()),
                            UserStatus.valueOf(rs.getString("status").toUpperCase())
                        )
                );
            } catch (EmptyResultDataAccessException e) {
                return null;
            }
    }

}
