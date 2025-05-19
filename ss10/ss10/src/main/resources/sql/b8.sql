create database if not exists ss10;
use ss10;

drop table if exists ticket;

create table ticket (
                        ticket_id int auto_increment primary key,
                        movie_title varchar(255) not null,
                        show_time date not null,
                        total_amount double not null,
                        seats_id varchar(255) not null
);

delimiter //

drop procedure if exists book_ticket;
create procedure book_ticket (
    in p_movietitle varchar(255),
    in p_showtime datetime,
    in p_totalamount double,
    in p_seats_id varchar(255)
)
begin
    insert into ticket (movie_title, show_time, total_amount, seats_id)
    values (p_movietitle, p_showtime, p_totalamount, p_seats_id);
end //

drop procedure if exists get_all_ticket;
create procedure get_all_ticket ()
begin
    select ticket_id, movie_title, show_time, total_amount, seats_id from ticket;
end //

drop procedure if exists get_ticket_by_id;
create procedure get_ticket_by_id(in_id int)
begin
    select ticket_id, movie_title, show_time, total_amount, seats_id from ticket
    where ticket_id = in_id;
end //

delimiter ;
