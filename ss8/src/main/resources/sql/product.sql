drop database if exists ss8;
create database if not exists ss8;
use ss8;

drop table if exists product;
create table if not exists product(
    id int auto_increment primary key,
    name varchar(150) not null,
    price decimal(15,2) check ( price > 0 ) not null,
    stock int check ( stock > 0 ) not null
);

delimiter //

drop procedure if exists find_all_product;
create procedure find_all_product()
begin
    select id, name, price, stock from product;
end //

drop procedure if exists save_product;
create procedure save_product(
    in_name varchar(150),
    in_price decimal(15,2),
    in_stock int
)
begin
    insert into product(name, price, stock)
        values(in_name, in_price, in_stock);
end //

delimiter ;