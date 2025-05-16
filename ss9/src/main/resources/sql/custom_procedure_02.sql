create database if not exists ss9;
use ss9;

delimiter //

drop procedure if exists get_all_customer;
create procedure get_all_customer()
begin
    select c_id, c_username, c_password, c_phone, c_address, c_gender from customer;
end //

drop procedure if exists save_customer;
create procedure save_customer(
    in_username varchar(150),
    in_password varchar(16),
    in_phone varchar(10),
    in_address varchar(150),
    in_gender enum('MALE', 'FEMALE', 'OTHER')
)
begin
    insert into customer(c_username, c_password, c_phone, c_address, c_gender)
    values(in_username, in_password, in_phone, in_address ,in_gender);
end //

drop procedure if exists update_customer;
create procedure update_customer(
    in_id int,
    in_username varchar(150),
    in_password varchar(16),
    in_phone varchar(10),
    in_address varchar(150),
    in_gender enum('MALE', 'FEMALE', 'OTHER')
)
begin
    update customer
    set
        c_username = in_username,
        c_password = in_password,
        c_phone = in_phone,
        c_address = in_address,
        c_gender = in_gender
    where c_id = in_id;
end //

drop procedure if exists delete_customer;
create procedure delete_customer(in_in int)
begin
    delete from customer
    where c_id = in_in;
end //

drop procedure if exists get_customer_by_id;
create procedure get_customer_by_id(in_id int)
begin
    select c_id, c_username, c_password, c_phone, c_address, c_gender from customer where c_id = in_id;
end //

drop procedure if exists get_customer_by_username;
create procedure get_customer_by_username(in_username varchar(150))
begin
    select c_id, c_username, c_password, c_phone, c_address, c_gender from customer where c_username = in_username;
end //
delimiter ;