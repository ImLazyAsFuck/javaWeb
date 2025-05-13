use ss6;

drop table if exists users;
create table if not exists users (
                                     id int auto_increment primary key,
                                     username varchar(50) not null unique,
                                     password varchar(100) not null,
                                     email varchar(100) not null unique,
                                     phone varchar(20),
                                     status boolean default true,
                                     created_at timestamp default current_timestamp
);

delimiter //
drop procedure if exists add_user;
create procedure add_user(in p_username varchar(50), in p_password varchar(100), in p_email varchar(100), in p_phone varchar(20))
begin
    insert into users(username, password, email, phone) values (p_username, p_password, p_email, p_phone);
end //

drop procedure if exists find_user_by_username;
create procedure find_user_by_username(in p_username varchar(50))
begin
    select * from users where username = p_username and status = true;
end //

drop procedure if exists find_user_by_email;
create procedure find_user_by_email(in p_email varchar(100))
begin
    select * from users where email = p_email and status = true;
end //

drop procedure if exists find_all_users;
create procedure find_all_users()
begin
    select * from users;
end //

drop procedure if exists update_user_status;
create procedure update_user_status(in p_id int, in p_status boolean)
begin
    update users set status = p_status where id = p_id;
end //

delimiter ;
