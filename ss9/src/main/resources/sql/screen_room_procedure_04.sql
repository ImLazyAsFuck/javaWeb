create database if not exists ss9;
use ss9;

delimiter //

drop procedure if exists get_all_screen_room;
create procedure get_all_screen_room()
begin
    select sr_id, sr_name, sr_total_seats from screen_room;
end //

drop procedure if exists get_screen_room_by_id;
create procedure get_screen_room_by_id(in_sr_id bigint)
begin
    select sr_id, sr_name, sr_total_seats from screen_room
    where sr_id = in_sr_id;
end //

delimiter ;