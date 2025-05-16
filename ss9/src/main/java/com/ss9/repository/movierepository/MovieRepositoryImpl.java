package com.ss9.repository.movierepository;

import com.ss9.model.Movie;
import com.ss9.utils.DBConnect;
import org.springframework.stereotype.Repository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MovieRepositoryImpl implements MovieRepository{
    @Override
    public List<Movie> findAll(){
        Connection con = null;
        CallableStatement cs = null;
        List<Movie> movies = new ArrayList<>();
        try{
            con = DBConnect.getConnection();
            cs = con.prepareCall("{call get_all_movie()}");
            ResultSet result = cs.executeQuery();
            System.out.println(result);
            while(result.next()){
                Movie movie = new Movie();
                movie.setId(result.getLong("m_id"));
                movie.setTitle(result.getString("m_title"));
                movie.setDirector(result.getString("m_director"));
                movie.setGenre(result.getString("m_genre"));
                movie.setLanguage(result.getString("m_language"));
                movie.setDescription(result.getString("m_description"));
                movie.setDuration(result.getInt("m_duration"));
                movies.add(movie);
            }
        }catch(Exception e){
            e.fillInStackTrace();
        }finally{
            DBConnect.closeConnection(con, cs);
        }
        return movies;
    }

    @Override
    public Movie findById(Long id){
        Connection con = null;
        CallableStatement cs = null;
        Movie movie = null;
        try{
            con = DBConnect.getConnection();
            cs = con.prepareCall("{call get_movie_by_id(?)}");
            cs.setLong(1, id);
            ResultSet result = cs.executeQuery();
            while(result.next()){
                movie = new Movie();
                movie.setId(result.getLong("m_id"));
                movie.setTitle(result.getString("m_title"));
                movie.setDirector(result.getString("m_director"));
                movie.setGenre(result.getString("m_genre"));
                movie.setLanguage(result.getString("m_language"));
                movie.setDescription(result.getString("m_description"));
                movie.setDuration(result.getInt("m_duration"));
            }
        }catch(Exception e){
            e.fillInStackTrace();
        }finally{
            DBConnect.closeConnection(con, cs);
        }
        return movie;
    }
}
