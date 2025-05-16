create database if not exists ss9;
use ss9;

delimiter //

drop procedure if exists get_all_schedule;
create procedure get_all_schedule()
begin
    select sc_id, m_id, sc_show_time, sr_id, sc_available_seats, sc_format from schedule;
end //

drop procedure if exists get_all_schedule_by_movie_id;
create procedure get_all_schedule_by_movie_id(
    in_m_id bigint
)
begin
    select sc_id, m_id, sc_show_time, sr_id, sc_available_seats, sc_format from schedule
    where m_id = in_m_id;
end //

create procedure get_schedule_by_id(id bigint)
begin
    select sc_id, m_id, sc_show_time, sr_id, sc_available_seats, sc_format from schedule
        where sc_id = id;
end //

delimiter ;