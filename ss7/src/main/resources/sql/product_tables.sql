drop database if exists ss7;
create database ss7;
use ss7;

drop table if exists product;
create table product(
    id int primary key,
    name varchar(150) not null,
    price decimal(15,2) check ( price > 0),
    stock int check ( stock > 0 ),
    description varchar(255),
    image varchar(150)
);

delimiter //

drop procedure if exists find_all_product;
create procedure find_all_product()
begin
    select id, name, price, stock, description, image from product;
end //


drop procedure if exists add_product;
create procedure add_product(
    in_name varchar(150),
    in_price decimal(15,2),
    in_stock int,
    in_description varchar(255),
    in_image varchar(150)
)
begin
    insert into product(name, price, stock, description, image)
        values(in_name, in_price,in_stock, in_description, in_image);
end //

drop procedure if exists find_product_by_id;
create procedure find_product_by_id(in_id int)
begin
    select id, name, price, stock, description, image from product
        where id = in_id;
end //

drop procedure if exists update_product;
create procedure update_product(
    in_id int,
    in_name varchar(150),
    in_price decimal(15,2),
    in_stock int,
    in_description varchar(255),
    in_image varchar(150)
)
begin
    update product
        set
            name = in_name,
            price = in_price,
            stock = in_stock,
            description = in_description,
            image = in_image
    where id = in_id;
end //

drop procedure if exists delete_product;
create procedure delete_product(in_id int)
begin
    delete from product
        where id = in_id;
end //

delimiter ;