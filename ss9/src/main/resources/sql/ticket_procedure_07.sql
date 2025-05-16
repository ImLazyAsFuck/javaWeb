create database if not exists ss9;
use ss9;

delimiter //

drop procedure if exists get_seats_by_schedule;
create procedure get_seats_by_schedule(
    in in_sc_id bigint
)
begin
    select seat.s_id, seat.sr_id, seat.s_price, seat.s_status 
    from seat
    inner join schedule on seat.sr_id = schedule.sr_id
    where schedule.sc_id = in_sc_id;
end //

drop procedure if exists get_schedule_details;
create procedure get_schedule_details(
    in in_sc_id bigint
)
begin
    select 
        sr.sr_name as screen_room_name,
        sc.sc_show_time as show_time,
        m.m_title as movie_title,
        sc.sc_available_seats as available_seats,
        sc.sc_format as format
    from schedule sc
    inner join screen_room sr on sc.sr_id = sr.sr_id
    inner join movie m on sc.m_id = m.m_id
    where sc.sc_id = in_sc_id;
end //

drop procedure if exists calculate_ticket_price;
create procedure calculate_ticket_price(
    in in_seat_ids text
)
begin
    declare total_price decimal(15,2);
    select sum(s_price) into total_price
    from seat
    where find_in_set(s_id, in_seat_ids) > 0;
    select total_price as ticket_total_price;
end //

drop procedure if exists book_ticket;
create procedure book_ticket(
    in in_customer_id bigint,
    in in_schedule_id bigint,
    in in_seat_ids bigint
)
begin
    declare ticket_id bigint;
    declare total_price decimal(15,2);
    declare selected_seat_count int;
    declare seat_id_var bigint;
    declare done int default false;
    declare seat_cursor cursor for 
        select s_id from seat 
        where find_in_set(s_id, in_seat_ids) > 0;
    declare continue handler for not found set done = true;
    
    start transaction;
    select sum(s_price), count(s_id) into total_price, selected_seat_count
    from seat
    where find_in_set(s_id, in_seat_ids) > 0;
    if (select count(*) from seat where find_in_set(s_id, in_seat_ids) > 0 and s_status = 1) > 0 then
        select 'Some selected seats are already booked.' as message;
        rollback;
    else
        insert into ticket(c_id, sc_id, t_total_price, t_created_at)
        values(in_customer_id, in_schedule_id, total_price, curdate());

        set ticket_id = last_insert_id();
        open seat_cursor;
        
        read_loop: loop
            fetch seat_cursor into seat_id_var;
            if done then
                leave read_loop;
            end if;
            update seat set s_status = 1 where s_id = seat_id_var;
            insert into ticket_seat(t_id, s_id) values(ticket_id, seat_id_var);
        end loop;
        
        close seat_cursor;
        update schedule 
        set sc_available_seats = sc_available_seats - selected_seat_count
        where sc_id = in_schedule_id;
        select 
            ticket_id as t_id,
            total_price as total_price,
            'Ticket booked successfully.' as message;
        
        commit;
    end if;
end //

drop procedure if exists get_ticket_details;
create procedure get_ticket_details(
    in in_ticket_id bigint
)
begin
    select 
        t.t_id as ticket_id,
        t.t_total_price as total_price,
        t.t_created_at as booking_date,
        m.m_title as movie_title,
        sc.sc_show_time as show_time,
        sr.sr_name as screen_room,
        group_concat(s.s_id) as seat_ids
    from ticket t
    inner join schedule sc on t.sc_id = sc.sc_id
    inner join movie m on sc.m_id = m.m_id
    inner join screen_room sr on sc.sr_id = sr.sr_id
    inner join ticket_seat ts on t.t_id = ts.t_id
    inner join seat s on ts.s_id = s.s_id
    where t.t_id = in_ticket_id
    group by t.t_id;
end //

delimiter ;