package com.ss11.repository.impl;

import com.ss11.model.Movie;
import com.ss11.repository.MovieDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import javax.validation.constraints.NotNull;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MovieDAOImpl implements MovieDAO{

    @Autowired
    private DataSource dataSource;

    @Override
    public List<Movie> getAll(){
        Connection con = null;
        CallableStatement cs = null;
        List<Movie> movies = new ArrayList<>();
        try {
            con = dataSource.getConnection();
            cs = con.prepareCall("{call get_all_movies()}");
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                Movie movie = new Movie();
                movie.setId(rs.getInt("id"));
                movie.setTitle(rs.getString("title"));
                movie.setDirector(rs.getString("director"));
                movie.setReleaseDate(rs.getDate("releaseDate").toLocalDate());
                movie.setPoster(rs.getString("poster"));
                movie.setGenre(rs.getString("genre"));
                movies.add(movie);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (cs != null) cs.close();
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
        return movies;
    }

    @Override
    public Movie get(int id) {
        Connection con = null;
        CallableStatement cs = null;
        Movie movie = null;
        try {
            con = dataSource.getConnection();
            cs = con.prepareCall("{call get_movie_by_id(?)}");
            cs.setInt(1, id);
            ResultSet rs = cs.executeQuery();
            if (rs.next()) {
                movie = new Movie();
                movie.setId(rs.getInt("id"));
                movie.setTitle(rs.getString("title"));
                movie.setDirector(rs.getString("director"));
                movie.setReleaseDate(rs.getDate("releaseDate").toLocalDate());
                movie.setPoster(rs.getString("poster"));
                movie.setGenre(rs.getString("genre"));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (cs != null) cs.close();
                if (con != null) con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return movie;
    }

    @Override
    public void save(Movie movie) {
        Connection con = null;
        CallableStatement cs = null;
        try {
            con = dataSource.getConnection();
            cs = con.prepareCall("{call add_movie(?,?,?,?,?)}");
            cs.setString(1, movie.getTitle());
            cs.setString(2, movie.getDirector());
            cs.setDate(3, Date.valueOf(movie.getReleaseDate()));
            cs.setString(4, movie.getGenre());
            cs.setString(5, movie.getPoster());
            cs.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (cs != null) cs.close();
                if (con != null) con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void update(Movie movie) {
        Connection con = null;
        CallableStatement cs = null;
        try {
            con = dataSource.getConnection();
            cs = con.prepareCall("{call update_movie(?,?,?,?,?,?)}");
            cs.setInt(1, movie.getId());
            cs.setString(2, movie.getTitle());
            cs.setString(3, movie.getDirector());
            cs.setDate(4, Date.valueOf(movie.getReleaseDate()));
            cs.setString(5, movie.getGenre());
            cs.setString(6, movie.getPoster());
            cs.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (cs != null) cs.close();
                if (con != null) con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void delete(int id) {
        Connection con = null;
        CallableStatement cs = null;
        try {
            con = dataSource.getConnection();
            cs = con.prepareCall("{call delete_movie(?)}");
            cs.setInt(1, id);
            cs.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (cs != null) cs.close();
                if (con != null) con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}

