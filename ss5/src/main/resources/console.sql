drop database if exists ss5;
create database ss5;
use ss5;

create table product(
    p_id int primary key auto_increment,
    p_name varchar(150) not null,
    p_description varchar(255),
    p_price decimal(15,2) check ( p_price > 0 )
);

delimiter //
drop procedure if exists get_all_product;
create procedure get_all_product()
begin
    select p_id, p_name, p_description, p_price from product;
end //
delimiter ;

