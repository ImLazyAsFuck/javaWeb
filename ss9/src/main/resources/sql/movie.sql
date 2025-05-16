create database if not exists ss9;
use ss9;

drop table if exists customer;
create table customer(
                         id int auto_increment primary key,
                         username varchar(150) not null unique,
                         password varchar(16) not null,
                         phone varchar(10) not null unique,
                         address varchar(150),
                         gender enum('MALE', 'FEMALE', 'OTHER') not null
);

drop table if exists movie;
create table movie(
    id int primary key auto_increment,
    title varchar(150) not null,
    director varchar(150) not null,
    genre varchar(150) not null,
    description varchar(255),
    duration int check ( duration > 0 ),
    language varchar(150) not null
);

drop table if exists schedule;
drop table if exists screen_room;

create table screen_room(
                            sr_id int primary key auto_increment,
                            sr_name varchar(150) not null,
                            sr_total_seats int check ( sr_total_seats > 0 )
);

create table schedule(
                         sc_id int primary key auto_increment,
                         sc_movie_title varchar(150) not null,
                         sc_show_time date default(curdate()),
                         sr_id int not null,
                         sc_available_seats int,
                         sc_format enum('TWO_D', 'THREE_D'),
                         foreign key (sr_id) references screen_room(sr_id)
);

delimiter //

drop procedure if exists get_all_movie;
create procedure get_all_movie()
begin
    select id, title, director, genre, description, duration, language from movie;
end //

drop procedure if exists get_movie_by_id;
create procedure get_movie_by_id(in_id int)
begin
    select id, title, director, genre, description, duration, language from movie
    where id = in_id;
end //


delimiter ;



delimiter //

drop procedure if exists get_all_schedule;
create procedure get_all_schedule()
begin
    select sc_id, sc_movie_title, sc_show_time, sr_id, sc_available_seats, sc_format from schedule;
end //

drop procedure if exists find_all_schedule_by_movie;
create procedure find_all_schedule_by_movie(in movie_title varchar(150))
begin
    select sc_id, sc_movie_title, sc_show_time, sr_id, sc_available_seats, sc_format
    from schedule
    where sc_movie_title = movie_title
      and sc_show_time >= curdate()
    order by sc_show_time;
end //

drop procedure if exists get_all_screen_room;
create procedure get_all_screen_room()
begin
    select sr_id, sr_name, sr_total_seats from screen_room;
end //

drop procedure if exists get_screen_room_by_id;
create procedure get_screen_room_by_id(in_sr_id int)
begin
    select sr_id, sr_name, sr_total_seats from screen_room
    where sr_id = in_sr_id;
end //

drop procedure if exists get_all_schedule_by_movie_title;
create procedure get_all_schedule_by_movie_title(
    in_movie_title varchar(150)
)
begin
    select sc_id, sc_movie_title, sc_show_time, sr_id, sc_available_seats, sc_format from schedule
    where sc_movie_title like concat('%', in_movie_title, '%');
end //

delimiter ;