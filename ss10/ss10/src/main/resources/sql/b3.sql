create database if not exists ss10;
use ss10;

drop table if exists account;
create table account(
    id int primary key auto_increment,
    username varchar(150) not null unique,
    email varchar(150) not null unique,
    password varchar(150) not null

);

delimiter //

drop procedure if exists register;
create procedure register(
    in_username varchar(150),
    in_password varchar(15),
    in_email varchar(150)
)
begin
    insert into account(username, email, password)
        values(in_username, in_email, in_password);
end //

drop procedure if exists login;
create procedure login(
    in_username varchar(150),
    in_password varchar(15)
)
begin
    select id, username, email, password from account
           where username = in_username and password = in_password;
end //

delimiter ;