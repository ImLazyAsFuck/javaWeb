create database if not exists ss16;
use ss16;

drop table if exists User;
create table User(
    id int auto_increment primary key,
    username varchar(150) not null unique,
    password varchar(150) not null,
    email varchar(150) not null unique,
    role enum('ADMIN', 'USER') not null default('USER'),
    status enum('ACTIVE', 'BLOCKED') not null default ('ACTIVE')
);

delimiter //

drop procedure if exists login;
create procedure login(
    p_username varchar(150),
    p_password varchar(150)
)
begin
    select id, username, password, email, role, status from user
        where username = p_username and password = p_password;
end //

drop procedure if exists register;
create procedure register(
    p_username varchar(150),
    p_password varchar(150),
    p_email varchar(150)
)
begin
    insert into User(username, password, email)
        values (p_username, p_password, p_email);
end //

drop procedure if exists unique_username;
create procedure unique_username(p_username varchar(150))
begin
    select count(id) from User
        where username = p_username;
end //

drop procedure if exists unique_email;
create procedure unique_email(p_email varchar(150))
begin
    select count(id) from User
    where email = p_email;
end //

delimiter ;

insert into User(username, password, email, role)
    values ('admin', 'password123', 'admin@gmail.com', 'ADMIN');