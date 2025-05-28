create database if not exists ss14;
use ss14;

drop table if exists User;
create table User(
    username varchar(150) primary key,
    password varchar(20) not null,
    email varchar(150) not null
);

delimiter //

drop procedure if exists save_user;
create procedure save_user(
    in_u_name varchar(150),
    in_p varchar(20),
    in_email varchar(150)
)
begin
    insert into User(username, password, email)
        values(in_u_name, in_p, in_email);
end //

delimiter ;
