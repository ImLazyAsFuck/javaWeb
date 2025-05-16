create database if not exists ss9;
use ss9;

delimiter //

drop procedure if exists get_all_movie;
create procedure get_all_movie()
begin
    select m_id, m_title, m_director, m_genre, m_description, m_duration, m_language from movie;
end //

drop procedure if exists get_movie_by_id;
create procedure get_movie_by_id(in_id int)
begin
    select m_id, m_title, m_director, m_genre, m_description, m_duration, m_language from movie
    where m_id = in_id;
end //


delimiter ;
