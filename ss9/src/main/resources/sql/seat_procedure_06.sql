create database if not exists ss9;
use ss9;

delimiter //

drop procedure if exists get_all_seat;
create procedure get_all_seat()
begin
    select s_id, sr_id, s_price, s_status from seat;
end //

drop procedure if exists get_all_seat_by_room_id;
create procedure get_all_seat_by_room_id(
    in_id bigint
)
begin
    select s_id, sr_id, s_price, s_status from seat where sr_id = in_id;
end //

drop procedure if exists get_seat_by_id;
create procedure get_seat_by_id(in_id bigint)
begin
    select s_id, sr_id, s_price, s_status from seat where s_id = in_id;
end //

delimiter ;